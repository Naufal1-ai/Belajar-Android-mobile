package com.kotlin.basic.trpl3c.intent

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl3c.R

class NilaiAkhirActivity : AppCompatActivity() {
    private lateinit var tvNim: TextView
    private lateinit var tvNama: TextView
    private lateinit var tvProdi: TextView
    private lateinit var tvJenisKelamin: TextView
    private lateinit var tvWaktu: TextView
    private lateinit var tvKehadiran: TextView
    private lateinit var tvTugas: TextView
    private lateinit var tvUts: TextView
    private lateinit var tvUas: TextView
    private lateinit var tvNilaiAkhir: TextView
    private lateinit var tvNilaiHuruf: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nilai_akhir)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvNim = findViewById(R.id.tvNIM)
        tvNama = findViewById(R.id.tvNama)
        tvProdi = findViewById(R.id.tvProdi)
        tvJenisKelamin = findViewById(R.id.tvJenisKelamin)
        tvWaktu = findViewById(R.id.tvWaktu)
        tvKehadiran = findViewById(R.id.tvKehadiran)
        tvTugas = findViewById(R.id.tvTugas)
        tvUts = findViewById(R.id.tvUts)
        tvUas = findViewById(R.id.tvUas)
        tvNilaiAkhir = findViewById(R.id.tvNilaiAkhir)
        tvNilaiHuruf = findViewById(R.id.tvNilaiHuruf)
    }

    override fun onStart() {
        super.onStart()
        nilai()
    }

    private fun nilai() {
        val nim = intent.getStringExtra("nim") ?: intent.getStringExtra("NIM")
        val nama = intent.getStringExtra("nama")
        val prodi = intent.getStringExtra("prodi")
        val jenisKelamin = intent.getStringExtra("jeniskelamin")
        val waktu = intent.getStringExtra("waktu")
        val kehadiran = intent.getIntExtra("kehadiran", 0)
        val tugas = intent.getIntExtra("tugas", 0)
        val uts = intent.getIntExtra("uts", 0)
        val uas = intent.getIntExtra("uas", 0)
        val nilaiAkhir = intent.getDoubleExtra("nilaiAkhir", 0.0)
        val nilaiHuruf = intent.getStringExtra("nilaiHuruf")

        tvNim.text = "NIM : $nim"
        tvNama.text = "Nama : $nama"
        tvProdi.text = "Program Studi : $prodi"
        tvJenisKelamin.text = "Jenis Kelamin : $jenisKelamin"
        tvWaktu.text = "Waktu Input : $waktu"
        tvKehadiran.text = "Kehadiran : $kehadiran"
        tvTugas.text = "Tugas : $tugas"
        tvUts.text = "UTS : $uts"
        tvUas.text = "UAS : $uas"
        tvNilaiAkhir.text = "Nilai Akhir : $nilaiAkhir"
        tvNilaiHuruf.text = "Nilai Huruf : $nilaiHuruf"
    }
}
