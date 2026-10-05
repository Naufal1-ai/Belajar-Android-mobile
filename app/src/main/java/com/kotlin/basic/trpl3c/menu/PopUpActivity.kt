package com.kotlin.basic.trpl3c.menu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kotlin.basic.trpl3c.R
import com.kotlin.basic.trpl3c.intent.NilaiIntentActivity
import com.kotlin.basic.trpl3c.ui.widget.PembayaranActivity

class PopUpActivity : AppCompatActivity() {
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pop_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolBar = findViewById(R.id.toolBar)


        setSupportActionBar(toolBar)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pop_up_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.menuCamera -> {
                startActivity(Intent(this, NilaiIntentActivity::class.java))
                true
            }
            R.id.menuGroup ->{
                startActivity(Intent(this, PembayaranActivity::class.java))
                true
            }
            else ->super.onOptionsItemSelected(item)

        }

    }
}