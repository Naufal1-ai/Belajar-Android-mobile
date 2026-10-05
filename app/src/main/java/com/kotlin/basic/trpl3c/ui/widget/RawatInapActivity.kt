package com.kotlin.basic.trpl3c.ui.widget

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
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

class RawatInapActivity : AppCompatActivity() {
    private lateinit var etNoReg: EditText
    private lateinit var etNama: EditText
    private lateinit var etNik: EditText
    private lateinit var etHp: EditText
    private lateinit var etTanggalMasuk: EditText
    private lateinit var etJamMasuk: EditText
    private lateinit var spnKelasKamar: Spinner
    private lateinit var rgKategoriPasien: RadioGroup
    private lateinit var etJumlahHari: EditText
    private lateinit var cbBedSofa: CheckBox
    private lateinit var cbSofa: CheckBox
    private lateinit var cbDispenser: CheckBox
    private lateinit var cbMakanan: CheckBox
    private lateinit var etBedSofa: EditText
    private lateinit var etSofa: EditText
    private lateinit var etDispenser: EditText
    private lateinit var etMakanan: EditText
    private lateinit var btnSimpan: Button
    private lateinit var tvHasil: TextView
    private lateinit var datePicker: DatePickerDialog.OnDateSetListener
    private lateinit var timePicker: TimePickerDialog.OnTimeSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rawat_inap)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNoReg = findViewById(R.id.etNoReg)
        etNama = findViewById(R.id.etNama)
        etNik = findViewById(R.id.etNik)
        etHp = findViewById(R.id.etHp)
        etTanggalMasuk = findViewById(R.id.etTanggalMasuk)
        etJamMasuk = findViewById(R.id.etJamMasuk)
        spnKelasKamar = findViewById(R.id.spnKelasKamar)
        rgKategoriPasien = findViewById(R.id.rgKategoriPasien)
        etJumlahHari = findViewById(R.id.etJumlahHari)
        cbBedSofa = findViewById(R.id.cbBedSofa)
        cbSofa = findViewById(R.id.cbSofa)
        cbDispenser = findViewById(R.id.cbDispenser)
        cbMakanan = findViewById(R.id.cbMakanan)
        etBedSofa = findViewById(R.id.etBedSofa)
        etSofa = findViewById(R.id.etSofa)
        etDispenser = findViewById(R.id.etDispenser)
        etMakanan = findViewById(R.id.etMakanan)
        btnSimpan = findViewById(R.id.btnSimpan)
        tvHasil = findViewById(R.id.tvHasil)
    }

    override fun onStart() {
        super.onStart()
        setupSpinnerKamar()
        setupDatePicker()
        setupTimePicker()
        bayar()
    }

    private fun setupSpinnerKamar() {
        val listKamar = arrayOf(
            "Kelas Super VIP",
            "Kelas VIP",
            "Kelas Utama",
            "Kelas I",
            "Kelas II",
            "Kelas III"
        )
        val adapter = ArrayAdapter(this, R.layout.spinner_style, listKamar)
        spnKelasKamar.adapter = adapter
    }

    private fun setupDatePicker() {
        val myCalendar = Calendar.getInstance()
        datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val formatIndo = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            etTanggalMasuk.setText(formatIndo.format(myCalendar.time))
        }

        etTanggalMasuk.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }

    private fun setupTimePicker() {
        val myTime = Calendar.getInstance()
        timePicker = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            myTime[Calendar.HOUR_OF_DAY] = hourOfDay
            myTime[Calendar.MINUTE] = minute
            val formatIndo = SimpleDateFormat("HH:mm", Locale.getDefault()).format(myTime.time)
            etJamMasuk.setText(formatIndo)
        }

        etJamMasuk.setOnClickListener {
            TimePickerDialog(
                this,
                timePicker,
                myTime[Calendar.HOUR_OF_DAY],
                myTime[Calendar.MINUTE],
                true
            ).show()
        }
    }

    private fun bayar() {
        val listBiayaTambah = arrayOf(cbBedSofa, cbSofa, cbDispenser, cbMakanan)
        val hargaBiayaTambah = intArrayOf(75_000, 60_000, 25_000, 45_000)
        val namaBiayaTambah = arrayOf(
            "Bed Sofa: Rp. 75.000/Hari",
            "Sofa: Rp.60.000/Hari",
            "Dispenser: Rp. 25.000/Hari",
            "Makanan(Nasi, Sambal): Rp. 45.000/Hari"
        )
        val jlhHariBiayaTambah = arrayOf(etBedSofa, etSofa, etDispenser, etMakanan)

        for (i in jlhHariBiayaTambah.indices) {
            jlhHariBiayaTambah[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(et: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (et.toString() != "0") {
                        listBiayaTambah[i].isChecked = !et.isNullOrEmpty()
                    }
                }
            })
        }

        btnSimpan.setOnClickListener {
            val noReg = etNoReg.text.toString()
            val nama = etNama.text.toString()
            val nik = etNik.text.toString()
            val hp = etHp.text.toString()
            val tglMasuk = etTanggalMasuk.text.toString().ifEmpty { "Belum dipilih" }
            val jamMasuk = etJamMasuk.text.toString().ifEmpty { "Belum dipilih" }
            val kelasKamar = spnKelasKamar.selectedItem?.toString() ?: "Belum dipilih"
            val jlhHari = etJumlahHari.text.toString().toIntOrNull() ?: 1

            val hargaKamar = when (kelasKamar) {
                "Kelas Super VIP" -> 750_000
                "Kelas VIP" -> 600_000
                "Kelas Utama" -> 400_000
                "Kelas I" -> 255_000
                "Kelas II" -> 170_000
                "Kelas III" -> 160_000
                else -> 0
            }

            val selectedRadioId = rgKategoriPasien.checkedRadioButtonId
            val kategoriPasien = if (selectedRadioId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioId)
                selectedRadioButton.text.toString()
            } else {
                "Belum dipilih"
            }

            val diskon = when (kategoriPasien) {
                "BPJS" -> 0.10
                "Non BPJS" -> 0.05
                "Mandiri" -> 0.03
                else -> 0.0
            }

            var totalBiayaTambah = 0
            var rincianBiayaTambah = ""
            for (i in listBiayaTambah.indices) {
                if (listBiayaTambah[i].isChecked) {
                    val hariItem = jlhHariBiayaTambah[i].text.toString().toIntOrNull() ?: jlhHari
                    totalBiayaTambah += hargaBiayaTambah[i] * hariItem
                    rincianBiayaTambah += "\n+ ${namaBiayaTambah[i]}"
                }
            }

            val totalKamar = hargaKamar * jlhHari
            val totalDiskon = (diskon * hargaKamar * jlhHari).toInt()
            val potongan = if (jlhHari > 3) 50_000 else 0
            val totalBayar = totalKamar + totalBiayaTambah - totalDiskon - potongan

            val hasil = """
                No Reg : $noReg
                Nama Lengkap : $nama
                NIK : $nik
                HP : $hp
                Tanggal Masuk : $tglMasuk
                Jam Masuk : $jamMasuk
                Kelas Kamar : $kelasKamar
                Kategori Pasien : $kategoriPasien
                Jumlah Hari : $jlhHari
                Biaya Tambahan :$rincianBiayaTambah
                Total Bayar : $totalBayar
            """.trimIndent()

            tvHasil.text = hasil
        }
    }
}