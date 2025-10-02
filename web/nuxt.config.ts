import vuetify, { transformAssetUrls } from "vite-plugin-vuetify";

// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: "2025-07-15",
  devtools: { enabled: true },
  modules: ["@nuxt/eslint", "@nuxt/scripts", "@nuxt/icon", "@nuxt/ui", "nuxt-security"],
  css: ["~/assets/css/main.css"],
  app: {
    head: {
      title: "WMS PRO",
    },
  },
  security: {
    strict: true,
  }
});