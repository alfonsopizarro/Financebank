package com.example.financebank

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_graficos.*
import kotlinx.android.synthetic.main.activity_ingresos.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class graficos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graficos)
        supportActionBar?.hide()

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