package com.example.financebank

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_graficos.*

class graficos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graficos)
        supportActionBar?.hide()

        val toast6 = Toast.makeText(
            applicationContext,
            "Zona de graficos", Toast.LENGTH_SHORT
        )
        toast6.show()

        botonvolvergraficos.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            startActivity(intent)
        }

        botonsalirgraficos.setOnClickListener {
            finish();{
        }
        }
    }
}