package com.example.financebank


import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registroactivity.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*


class pantallaprincipal: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)
        supportActionBar?.hide()

        val toast3 = Toast.makeText(
            applicationContext,
            "Bienvenido a tu area de Finance bank", Toast.LENGTH_SHORT
        )
        toast3.show()


        botongraficos.setOnClickListener{
            val intent= Intent(this,graficos::class.java)
            startActivity(intent)
        }

        botoningresos.setOnClickListener{
            val intent= Intent(this,ingresos::class.java)
            startActivity(intent)
        }

        botongastos.setOnClickListener{
            val intent= Intent(this,gastos::class.java)
            startActivity(intent)
        }

        botonhistorial.setOnClickListener{
            val intent= Intent(this,historial::class.java)
            startActivity(intent)
        }

        botonsalirpantallaprincipal.setOnClickListener {
            finish();{
        }
        }
    }

}