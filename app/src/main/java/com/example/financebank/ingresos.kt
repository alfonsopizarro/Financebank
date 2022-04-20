package com.example.financebank

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ingresos.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import kotlinx.android.synthetic.main.activity_registroactivity.*


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

        val extras = intent.extras
        var email = ""
        var nombre = ""
        if (extras != null) {
            email = extras.getString("email").toString()
            nombre = extras.getString("nombre").toString()
        }

        botonvolveringresos.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            intent.putExtra("email",email)
            intent.putExtra("nombre", nombre)
            startActivity(intent)
        }



        //tabla ingresos 13

        botonaceptaringresos.setOnClickListener {
            val admin = AdminSQLiteHelper(this,"FinanceBank", null, 1)
            val bd = admin.writableDatabase
            val ingreso = ContentValues()
            ingreso.put("concepto", nombreingresos.getText().toString())
            ingreso.put("fecha", fechaingresos.getText().toString())
            ingreso.put("esIngreso", true)
            ingreso.put("email", email)
            ingreso.put("cantidad", Integer.parseInt(cantidadingresos.getText().toString()))
            bd.insert("movimientos",null, ingreso)
            bd.close()
            Toast.makeText(this, "ingreso aplicado correctamente", Toast.LENGTH_SHORT).show()

        }

        }
    }
