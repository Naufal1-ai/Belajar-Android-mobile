package com.kotlin.basic.trpl3c.intent

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl3c.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NilaiIntentActivity : AppCompatActivity() {
    private lateinit var etNim: EditText
    private lateinit var etNama: EditText
    private lateinit var spinnerProdi: Spinner
    private lateinit var rgJk: RadioGroup
    private lateinit var tvTime: TextView
    private lateinit var etTgs: EditText
    private lateinit var etKhd: EditText
    private lateinit var etUts: EditText
    private lateinit var etUas: EditText
    private lateinit var btnSimpan: Button
    private lateinit var tvNilai: TextView

    private lateinit var timePicker: TimePickerDialog.OnTimeSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nilai_intent)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etNim = findViewById(R.id.etNIM)
        etNama = findViewById(R.id.etNama)
        spinnerProdi = findViewById(R.id.spinnerProdi)
        rgJk = findViewById(R.id.rgJK)
        tvTime = findViewById(R.id.tvTime)
        etTgs = findViewById(R.id.etTugas)
        etKhd = findViewById(R.id.etKhd)
        etUts = findViewById(R.id.etUTS)
        etUas = findViewById(R.id.etUAS)
        btnSimpan = findViewById(R.id.btnSimpan)
        tvNilai = findViewById(R.id.tvNilai)

    }

    override fun onStart() {
        super.onStart()
        setupSpinnerProdi()
        setupTimePicker()
        nilaiAkhir()
    }

    private fun setupSpinnerProdi() {
            val listProdi = arrayOf(
                "D4 Teknologi Rekayasa Perangkat Lunak",
                "D4 Teknik Informatika",
                "D3 Manajemen Informatika",
                "D4 Cyber Security",
                "D4 Animasi"
            )
            val adapter = ArrayAdapter(this, R.layout.spinner_style, listProdi)
            spinnerProdi.adapter = adapter
        }

        private fun setupTimePicker() {
            val myTime = Calendar.getInstance()
            timePicker = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                myTime[Calendar.HOUR_OF_DAY] = hourOfDay
                myTime[Calendar.MINUTE] = minute
                val formatIndo = SimpleDateFormat("HH:mm", Locale.getDefault()).format(myTime.time)
                tvTime.text = formatIndo
            }

            tvTime.setOnClickListener {
                TimePickerDialog(
                    this,
                    timePicker,
                    myTime[Calendar.HOUR_OF_DAY],
                    myTime[Calendar.MINUTE],
                    true
                ).show()
            }
        }

        private fun nilaiAkhir() {
            btnSimpan.setOnClickListener {
                val nim = etNim.text.toString()
                val nama = etNama.text.toString()
                val prodi = spinnerProdi.selectedItem?.toString() ?: "Belum dipilih"

                val selectedJkId = rgJk.checkedRadioButtonId
                val jenisKelamin = if (selectedJkId != -1) {
                    val selectedRadioButton = findViewById<RadioButton>(selectedJkId)
                    selectedRadioButton.text.toString()
                } else {
                    "Belum dipilih"
                }

                val waktu = tvTime.text.toString().ifEmpty { "Belum dipilih" }

                val tgs = etTgs.text.toString().toIntOrNull() ?: 0
                val khd = etKhd.text.toString().toIntOrNull() ?: 0
                val uts = etUts.text.toString().toIntOrNull() ?: 0
                val uas = etUas.text.toString().toIntOrNull() ?: 0

                val nilaiAngka = (khd * 0.1) + (tgs * 0.2) + ((uts + uas) / 2.0 * 0.7)
                val nilaiHuruf = when (nilaiAngka) {
                    in 90.0..100.0 -> "A"
                    in 81.0..89.9 -> "A-"
                    in 76.0..80.9 -> "B+"
                    in 71.0..75.9 -> "B"
                    in 66.0..70.9 -> "B-"
                    in 61.0..65.9 -> "C+"
                    in 51.0..60.9 -> "C"
                    in 46.0..50.9 -> "D"
                    else -> "E"
                }

                val hasil = """
                    NIM : $nim
                    Nama : $nama
                    Program Studi : $prodi
                    Jenis Kelamin : $jenisKelamin
                    Waktu Input : $waktu
                    Nilai Kehadiran : $khd
                    Nilai Tugas : $tgs
                    Nilai UTS : $uts
                    Nilai UAS : $uas
                    Nilai Angka : $nilaiAngka
                    Nilai Huruf : $nilaiHuruf
                """.trimIndent()

                tvNilai.text = hasil

                // untuk berpindah dari NilaiIntentActivity ke NilaiAkhirActivity
                val intent = Intent(this, NilaiAkhirActivity::class.java).apply {
                    putExtra("nim", nim)
                    putExtra("nama", nama)
                    putExtra("prodi", prodi.toString())
                    putExtra("jeniskelamin", jenisKelamin)
                    putExtra("waktu", waktu)
                    putExtra("kehadiran", khd)
                    putExtra("tugas", tgs)
                    putExtra("uts", uts)
                    putExtra("uas", uas)
                    putExtra("nilaiAkhir", nilaiAngka)
                    putExtra("nilaiHuruf", nilaiHuruf)
                }
                startActivity(intent)
            }
        }
    }