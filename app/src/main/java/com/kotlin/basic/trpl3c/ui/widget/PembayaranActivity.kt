package com.kotlin.basic.trpl3c.ui.widget

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl3c.R

class PembayaranActivity : AppCompatActivity() {
    private lateinit var cbNasGor: CheckBox
    private lateinit var cbMieGor: CheckBox
    private lateinit var cbSoto: CheckBox
    private lateinit var cbLontong: CheckBox
    private lateinit var cbAG: CheckBox

    private lateinit var etNasGor: EditText
    private lateinit var etMieGor: EditText
    private lateinit var etSoto: EditText
    private lateinit var etLontong: EditText
    private lateinit var etAG: EditText
    private lateinit var  spnJnsPelanggan: Spinner
    private lateinit var btnBayar: Button
    private lateinit var tvTotal: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pembayaran)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cbNasGor = findViewById(R.id.cbNasgor)
        cbMieGor = findViewById(R.id.cbMiegor)
        cbSoto = findViewById(R.id.cbSoto)
        cbLontong = findViewById(R.id.cbLontong)
        cbAG = findViewById(R.id.cbAGep)
        etNasGor = findViewById(R.id.etNasgor)
        etMieGor = findViewById(R.id.etMiegor)
        etSoto = findViewById(R.id.etSoto)
        etLontong = findViewById(R.id.etLontong)
        etAG = findViewById(R.id.etAGep)
        btnBayar = findViewById(R.id.btnBayar)
        tvTotal = findViewById(R.id.tvTotal)
        spnJnsPelanggan = findViewById(R.id.spnjnsPelanggan)

    }

    override fun onStart() {
        super.onStart()
        jnsPelanggan()
        bayar()
    }

    private fun jnsPelanggan() {
        val listJnsPelanggan = arrayOf("VIP","Member","Reguler")
        spnJnsPelanggan.adapter = ArrayAdapter(this,
            R.layout.spinner_style,
            listJnsPelanggan)
    }

    private fun bayar() {
        //buat variabel untuk list makanan
        val listMakanan = arrayOf(cbNasGor,cbMieGor,cbSoto,cbLontong,cbAG)

        // variabel harga makanan
        val hargaMakanan = intArrayOf(12_000,10_000,12_000,8_000,15_000)

        //Buat variabel jumlah makanan
        val jlhMakanan = arrayOf(etNasGor,etMieGor,etSoto,etLontong,etAG)


        for(i in jlhMakanan.indices){
            jlhMakanan[i].addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(
                    p0: CharSequence?,
                    p1: Int,
                    p2: Int,
                    p3: Int
                ) {

                }

                override fun onTextChanged(
                    et: CharSequence?,
                    p1: Int,
                    p2: Int,
                    p3: Int
                ) {
                    if(et.toString()!= "0")
                        listMakanan[i].isChecked = !et.isNullOrEmpty()
                }
            })

        }
        btnBayar.setOnClickListener {
            var totalBayar = 0.0
            var pilMakanan = ""
            val pelanggan = spnJnsPelanggan.selectedItem.toString()
            val diskon = when (pelanggan) {
                "VIP" -> 0.02
                "Member" -> 0.01
                else -> 0.0
            }
            for(i in listMakanan.indices){
                if(listMakanan[i].isChecked){
                    val jlhMkn = jlhMakanan[i].text.toString().toIntOrNull() ?: 1
                    pilMakanan += "${listMakanan[i].text} x $jlhMkn = Rp. ${jlhMkn * hargaMakanan[i]}\n"
                    totalBayar += jlhMkn * hargaMakanan[i]
                }
            }

            val potonganHarga = if (totalBayar>=150_000) 1_000 else 0

            val totalDiskon = totalBayar * diskon // Menghitung diskon
            totalBayar -= totalDiskon
            totalBayar -= potonganHarga
            val pembayaran = "Pilihan makanan :\n$pilMakanan\n Total Diskon : $totalDiskon\n Total potongan :$potonganHarga\n Total Bayar : Rp. $totalBayar"

            tvTotal.text = pembayaran
        }

    }

}