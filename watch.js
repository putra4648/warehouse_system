const chokidar = require("chokidar");
const JavaScriptObfuscator = require("javascript-obfuscator");
const fs = require("fs");
const path = require("path");

// Paths from root project directory
const srcDir = "./src/main/resources/static/raw";
const outputDir = "./src/main/resources/static/obfuscate";

// Obfuscate a single file
function obfuscateFile(sourceFile, outputFile) {
  const code = fs.readFileSync(sourceFile, "utf8");
  const obfuscated = JavaScriptObfuscator.obfuscate(code, {
    compact: true,
    controlFlowFlattening: true,
    deadCodeInjection: true,
    stringArray: true,
    rotateStringArray: true,
    transformObjectKeys: true,
    unicodeEscapeSequence: false,
  });

  const outputDirPath = path.dirname(outputFile);
  if (!fs.existsSync(outputDirPath)) {
    fs.mkdirSync(outputDirPath, { recursive: true });
  }

  fs.writeFileSync(outputFile, obfuscated.getObfuscatedCode());
}

// Get relative path for output
function getOutputPath(srcPath) {
  const relativePath = path.relative(srcDir, srcPath);
  return path.join(outputDir, relativePath);
}

// Process all existing files
function processExistingFiles() {
  console.log("🔍 Processing existing files...");

  function processDirectory(dir) {
    if (!fs.existsSync(dir)) return;

    const items = fs.readdirSync(dir);
    items.forEach((item) => {
      const itemPath = path.join(dir, item);
      const stat = fs.statSync(itemPath);

      if (stat.isDirectory()) {
        processDirectory(itemPath);
      } else if (item.endsWith(".js")) {
        const outputPath = getOutputPath(itemPath);
        console.log(`   Processing: ${itemPath}`);
        try {
          obfuscateFile(itemPath, outputPath);
        } catch (error) {
          console.error(`   ❌ Error: ${error.message}`);
        }
      }
    });
  }

  processDirectory(srcDir);
}

// Initialize watcher
function startWatcher() {
  console.log("👀 Starting file watcher...");
  console.log(`📁 Source: ${path.resolve(srcDir)}`);
  console.log(`📁 Output: ${path.resolve(outputDir)}`);

  // Ensure directories exist
  if (!fs.existsSync(srcDir)) {
    console.error(
      `❌ Source directory does not exist: ${path.resolve(srcDir)}`
    );
    console.log(
      "💡 Please create the directory or check your path configuration."
    );
    process.exit(1);
  }

  if (!fs.existsSync(outputDir)) {
    fs.mkdirSync(outputDir, { recursive: true });
  }

  // Process existing files first
  processExistingFiles();

  const watcher = chokidar.watch(srcDir, {
    ignored: (path, stats) => stats?.isFile() && !path.endsWith(".js"), // only watch js files
    persistent: true,
    ignoreInitial: true, // Skip initial scan since we processed existing files
  });

  watcher
    .on("add", (filePath) => {
      const outputPath = getOutputPath(filePath);
      console.log(`➕ File added: ${filePath}`);
      try {
        obfuscateFile(filePath, outputPath);
        console.log(`   ✅ Obfuscated to: ${outputPath}`);
      } catch (error) {
        console.error(`   ❌ Error processing file: ${error.message}`);
      }
    })
    .on("change", (filePath) => {
      const outputPath = getOutputPath(filePath);
      console.log(`🔄 File changed: ${filePath}`);
      try {
        obfuscateFile(filePath, outputPath);
        console.log(`   ✅ Obfuscated to: ${outputPath}`);
      } catch (error) {
        console.error(`   ❌ Error processing file: ${error.message}`);
      }
    })
    .on("unlink", (filePath) => {
      const outputPath = getOutputPath(filePath);
      console.log("UNLINK EXECUTED");
      if (fs.existsSync(outputPath)) {
        fs.unlinkSync(outputPath);
        console.log(`🗑️  File deleted: ${filePath}`);
        console.log(`   ✅ Removed from: ${outputPath}`);
      }
    })
    .on("ready", () => {
      console.log("✅ File watcher ready. Monitoring for changes...");
    })
    .on("error", (error) => {
      console.error(`❌ Watcher error: ${error.message}`);
    });

  // Handle graceful shutdown
  process.on("SIGINT", () => {
    console.log("\n🛑 Stopping file watcher...");
    watcher.close();
    process.exit(0);
  });
}

startWatcher();
