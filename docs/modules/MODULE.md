# Modules

1. Modul Inbound (Manajemen Barang Masuk)
   Modul ini menangani segala sesuatu yang datang ke gudang.
   - Receiving: Pencatatan barang yang datang dari pemasok.
   - Put-away: Logika penempatan barang ke rak atau lokasi tertentu berdasarkan kategori atau frekuensi pengambilan (Fast/Slow moving).
   - Cross-Docking: Pemindahan barang langsung dari penerimaan ke pengiriman tanpa disimpan di rak (untuk efisiensi waktu).

2. Modul Inventory (Manajemen Stok)
   Jantung dari WMS untuk memastikan akurasi data barang di dalam gudang.
   - Stock Locator: Melacak posisi koordinat barang (Lorong, Rak, Level, Bin).
   - Cycle Counting: Fitur penghitungan stok berkala tanpa harus menghentikan operasional gudang secara total.
   - Inventory Adjustment: Modul untuk penyesuaian stok jika terjadi kerusakan, kehilangan, atau kesalahan input.
   - Multi-Warehouse Support: Pengelolaan stok di beberapa lokasi gudang yang berbeda dalam satu sistem.

3. Modul Outbound (Manajemen Barang Keluar)
   Fokus pada pemenuhan pesanan pelanggan dengan cepat dan akurat.
   - Order Management: Memproses pesanan yang masuk (Sales Order).
   - Picking: Strategi pengambilan barang (misal: Wave Picking, Batch Picking, atau Zone Picking).
   - Packing & Labeling: Proses pengemasan dan pencetakan label pengiriman atau barcode.
   - Shipping/Dispatch: Dokumentasi keberangkatan barang dan integrasi dengan kurir atau armada internal.

4. Modul Operasional & SDM
   - Labor Management: Memantau produktivitas staf gudang (siapa yang melakukan picking paling banyak, berapa lama waktu packing, dll).
   - Task Interleaving: Pengaturan tugas otomatis agar staf tidak berjalan dengan tangan kosong (misal: setelah meletakkan barang masuk, sistem mengarahkan staf untuk mengambil barang keluar di jalur yang sama).

5. Modul Pelaporan & Analytics
   - Dashboard Real-time: Visualisasi tingkat okupansi gudang dan status pesanan.
   - Reporting: Laporan stok kritis (reorder point), laporan barang kadaluarsa (FEFO), dan laporan turn-over barang.
