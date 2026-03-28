# Inbound Module

1. Validasi Dokumen (PO vs Actual)
   Sistem harus mampu membandingkan barang yang datang dengan pesanan pembelian (Purchase Order).
   - Over-receiving: Kebijakan jika barang yang datang lebih banyak dari yang dipesan (apakah ditolak atau diterima dengan catatan).
   - Partial Receipt: Kemampuan mencatat penerimaan barang yang dicicil (misal pesan 100, baru datang 40).
   - Blind Receiving: Opsi untuk menghitung barang tanpa melihat jumlah di PO (untuk memaksa staf menghitung fisik secara jujur).

2. Manajemen Identitas Barang (SKU & Batch)
   Setiap barang yang masuk harus teridentifikasi dengan unik.
   - Barcode/QR Scanner: Integrasi wajib agar input data cepat dan minim human error.
   - Lot & Batch Tracking: Sangat penting untuk industri makanan atau farmasi untuk melacak tanggal produksi.
   - Expiry Date (FEFO/FIFO): Pencatatan tanggal kadaluarsa di awal untuk menentukan logika pengeluaran barang nantinya.

3. Logika Penempatan (Put-away Strategy)
   Jangan biarkan staf menaruh barang di sembarang tempat. Sistem harus memberikan rekomendasi lokasi:
   - Zone Management: Memisahkan barang berdasarkan kategori (misal: barang pecah belah, barang cair, atau barang bernilai tinggi).
   - Velocity-based: Barang fast-moving diletakkan di rak yang paling dekat dengan area packing.
   - Capacity Check: Sistem harus tahu apakah rak nomor A1 masih muat atau sudah penuh berdasarkan dimensi/volume barang.

4. Quality Control (QC) & Karantina
   Tidak semua barang yang masuk bisa langsung dijual atau digunakan.
   - Status Barang: Berikan status seperti Available, Under Inspection, atau Damaged.
   - Hold/Quarantine: Fitur untuk mengunci stok yang sedang bermasalah agar tidak bisa diproses oleh modul Outbound secara tidak sengaja.

Flow Inbound:

`Terima PO -> Scan Barang -> Input QC -> Sistem Generate Put-away Tag -> Barang diletakkan di Rak -> Update Status Lokasi`
