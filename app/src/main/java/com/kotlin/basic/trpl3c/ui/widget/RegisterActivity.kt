package com.kotlin.basic.trpl3c.ui.widget

import android.app.DatePickerDialog
import android.os.Bundle
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

class RegisterActivity : AppCompatActivity() {
    // Variable Global
    private lateinit var etEmail: EditText
    private lateinit var etNama: EditText
    private lateinit var etPassword: EditText
    private lateinit var etTelepon: EditText
    private lateinit var tvTanggalLahir: TextView
    private lateinit var spnProdi: Spinner
    private lateinit var btnSimpan: Button
    private lateinit var tvHasil: TextView
    private lateinit var rgTamatan: RadioGroup
    private lateinit var cbProgrammer: CheckBox
    private lateinit var cbAi: CheckBox
    private lateinit var cbNetwork: CheckBox
    private lateinit var cbCyberSecurity: CheckBox
    private lateinit var datePicker: DatePickerDialog.OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inisialisasi
        etEmail = findViewById(R.id.etEmail)
        etNama = findViewById(R.id.etNama)
        etPassword = findViewById(R.id.etPassword)
        etTelepon = findViewById(R.id.etTelepon)
        tvTanggalLahir = findViewById(R.id.tvTanggalLahir)
        spnProdi = findViewById(R.id.spnProdi)
        btnSimpan = findViewById(R.id.btnSimpan)
        tvHasil = findViewById(R.id.tvHasil)
        rgTamatan = findViewById(R.id.rgTamatan)
        cbProgrammer = findViewById(R.id.cbProgrammer)
        cbAi = findViewById(R.id.cbAi)
        cbNetwork = findViewById(R.id.cbNetwork)
        cbCyberSecurity = findViewById(R.id.cbCyberSecurity)
    }

    override fun onStart() {
        super.onStart()
        setupSpinnerProdi()
        setupDatePicker()

        btnSimpan.setOnClickListener {
            val email = etEmail.text.toString()
            val nama = etNama.text.toString()
            val password = etPassword.text.toString()
            val telepon = etTelepon.text.toString()
            val tanggalLahir = tvTanggalLahir.text.toString().ifEmpty { "Belum dipilih" }
            val prodi = spnProdi.selectedItem?.toString() ?: "Belum dipilih"
            val selectedRadioId = rgTamatan.checkedRadioButtonId
            val tamatan = if (selectedRadioId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioId)
                selectedRadioButton.text.toString()
            } else {
                "Belum dipilih"
            }

            val keahlianList = mutableListOf<String>()
            if (cbProgrammer.isChecked) keahlianList.add(cbProgrammer.text.toString())
            if (cbAi.isChecked) keahlianList.add(cbAi.text.toString())
            if (cbNetwork.isChecked) keahlianList.add(cbNetwork.text.toString())
            if (cbCyberSecurity.isChecked) keahlianList.add(cbCyberSecurity.text.toString())

            val keahlian = if (keahlianList.isNotEmpty()) {
                keahlianList.joinToString(", ")
            } else {
                "Tidak ada"
            }

            tvHasil.text = """
                Email: $email
                Nama: $nama
                Password: $password
                Telepon: $telepon
                Tamatan: $tamatan
                Keahlian: $keahlian
                Program Studi: $prodi
                Tanggal Lahir: $tanggalLahir
            """.trimIndent()
        }
    }

    private fun setupSpinnerProdi() {
        val listProdi = arrayOf(
            "D4 Teknologi Rekayasa Perangkat Lunak",
            "D4 Teknik Informatika",
            "D3 Manajemen Informatika",
            "D4 Rekayasa Sistem Komputer",
            "D4 Animasi",
        )
        val adapter = ArrayAdapter(this, R.layout.spinner_style, listProdi)
        spnProdi.adapter = adapter
    }

    private fun setupDatePicker() {
        val myCalendar = Calendar.getInstance()
        datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val formatIndo = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
            tvTanggalLahir.text = formatIndo.format(myCalendar.time)
        }
        tvTanggalLahir.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH],
            ).show()
        }
    }
}