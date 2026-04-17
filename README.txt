EPS Flashcard Pro v3
====================

Fitur baru
- UI lebih modern (tema gelap, card modern, mode flashcard/quiz)
- Tombol suara Korea (TextToSpeech Android)
- Tombol favorit (tersimpan di perangkat)
- High score quiz tersimpan
- Bab 1-4 dari materi EPS TOPIK yang kamu upload
- Workflow GitHub Actions untuk build APK tanpa Android Studio

Cara build APK TANPA Android Studio (paling mudah)
1. Buat repository baru di GitHub.
2. Upload seluruh isi folder proyek ini ke repository tersebut.
3. Pastikan file .github/workflows/build-debug-apk.yml ikut ter-upload.
4. Buka tab Actions di GitHub.
5. Jalankan workflow: Build Debug APK.
6. Tunggu selesai.
7. Buka workflow yang sudah selesai lalu download artifact bernama:
   eps-flashcard-debug-apk
8. Di dalam artifact itu ada file:
   app-debug.apk

Cara build lewat command line lokal (jika sudah punya Android SDK)
1. Install JDK 17.
2. Install Android SDK packages:
   - platform-tools
   - platforms;android-34
   - build-tools;34.0.0
3. Dari folder project jalankan:
   gradle assembleDebug
4. APK hasil build ada di:
   app/build/outputs/apk/debug/app-debug.apk

Catatan penting
- Di lingkungan chat ini APK final tidak bisa dibuat langsung karena Android SDK/build tools penuh tidak tersedia.
- Tombol suara memakai mesin TextToSpeech bawaan Android. Jika suara Korea belum ada, install data voice Korea di perangkat.
