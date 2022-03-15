package com.example.financebank

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ingresos.*


class ingresos: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresos)
        supportActionBar?.hide()

        val toast4 = Toast.makeText(
            applicationContext,
            "Zona de ingresos", Toast.LENGTH_SHORT
        )
        toast4.show()

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