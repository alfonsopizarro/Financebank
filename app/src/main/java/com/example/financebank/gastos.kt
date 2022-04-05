package com.example.financebank

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gastos.*
import kotlinx.android.synthetic.main.activity_ingresos.*

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

        val extras = intent.extras
        var email = ""
        if (extras != null) {
            email = extras.getString("email").toString()

        }

        botonvolvergastos.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }


        //tabla gastos
        botonaceptargastos.setOnClickListener {
            val admin = AdminSQLiteHelper(this,"FinanceBank", null, 1)
            val bd = admin.writableDatabase
            val gasto = ContentValues()
            gasto.put("concepto", nombregastos.getText().toString())
            gasto.put("fecha", fechagastos.getText().toString())
            gasto.put("esIngreso", false)
            gasto.put("email", email)
            gasto.put("cantidad", Integer.parseInt(cantidadgastos.getText().toString()))
            bd.insert("movimientos",null, gasto)
            bd.close()
            Toast.makeText(this, "gasto aplicado correctamente", Toast.LENGTH_SHORT).show()

        }
    }
}