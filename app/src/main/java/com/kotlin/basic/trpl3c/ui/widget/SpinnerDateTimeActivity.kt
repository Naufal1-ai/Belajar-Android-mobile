package com.kotlin.basic.trpl3c.ui.widget

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl3c.R
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Calendar

class SpinnerDateTimeActivity : AppCompatActivity() {
    private lateinit var spnJurusan: Spinner
    private lateinit var tvTanggal: TextView
    private lateinit var datePicker: DatePickerDialog.OnDateSetListener
    private lateinit var tvTime: TextView
    private lateinit var timePicker: TimePickerDialog.OnTimeSetListener
    private lateinit var btnSimpan: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spinner_date_time)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        spnJurusan = findViewById(R.id.spinnerJurusan)
        tvTanggal = findViewById(R.id.tvTanggal)
        tvTime = findViewById(R.id.tvTime)
        btnSimpan = findViewById(R.id.btnSimpan)
        tvHasil = findViewById(R.id.tvHasil)
    }

    override fun onStart() {
        super.onStart()
        jurusan()
        tanggal()
        waktu()
        simpan()
    }

    private fun simpan() {
        btnSimpan.setOnClickListener {
            val jurusan = spnJurusan.selectedItem
            val tgl = tvTanggal.text
            val wkt = tvTime.text
            val hasil = "Jurusan: $jurusan\nTanggal: $tgl\nWaktu: $wkt"
            tvHasil.text = hasil
        }
    }

    private fun waktu() {
        val myTime = Calendar.getInstance()
        timePicker = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            myTime[Calendar.HOUR] = hour
            myTime[Calendar.MINUTE] = minute
            val formatIndo = SimpleDateFormat("HH:mm").format(myTime.time)
            tvTime.text = formatIndo
        }

        tvTime.setOnClickListener {
            TimePickerDialog(this,
                timePicker,
                myTime[Calendar.HOUR],
                myTime[Calendar.MINUTE],
                true)
                .show()
        }
    }

    private fun tanggal() {
        val myCalendar = Calendar.getInstance()
        datePicker = DatePickerDialog.OnDateSetListener {_, year, month, dayOfMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val formatIndo = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
            tvTanggal.text = formatIndo.format(myCalendar.time)
        }
        tvTanggal.setOnClickListener {
            DatePickerDialog(this,
                datePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH])
                .show()
        }
    }

    private fun jurusan() {
        val listJurusan = arrayOf("Teknologi Informasi", "Mesin", "Elektro", "Sipil", "BI", "Akuntansi", "AN")
        val adapter = ArrayAdapter(this, R.layout.spinner_style, listJurusan)
        spnJurusan.adapter = adapter
    }
}