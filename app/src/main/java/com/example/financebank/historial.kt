package com.example.financebank

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_historial.*

class historial: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)
        supportActionBar?.hide()

        val toast7 = Toast.makeText(
            applicationContext,
            "Zona de historial", Toast.LENGTH_SHORT
        )
        toast7.show()

        botonvolverhistorial.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            startActivity(intent)
        }


        }
    }
