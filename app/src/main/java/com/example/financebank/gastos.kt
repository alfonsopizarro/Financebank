package com.example.financebank

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gastos.*
import kotlinx.android.synthetic.main.activity_ingresos.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class gastos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gastos)
        supportActionBar?.hide()

        val toast5 = Toast.makeText(
            applicationContext,
            "Zona de gastos", Toast.LENGTH_SHORT
        )
        toast5.show()

        botonvolvergastos.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            startActivity(intent)
        }

        botonsalirgastos.setOnClickListener {
            finish();{
        }
        }
    }
}