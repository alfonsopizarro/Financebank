package com.example.financebank

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ingresos.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import kotlinx.android.synthetic.main.activity_registroactivity.*

class ingresos: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresos)
        supportActionBar?.hide()

        botonvolveringresos.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            startActivity(intent)
        }

        botonsaliringresos.setOnClickListener {
            finish();{
        }
        }
    }
}