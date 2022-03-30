package com.example.financebank



import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_historial.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import kotlinx.android.synthetic.main.activity_registroactivity.*


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


        botongraficos.setOnClickListener {
            val intent = Intent(this, graficos::class.java)
            startActivity(intent)
        }

        botoningresos.setOnClickListener {
            val intent = Intent(this, ingresos::class.java)
            startActivity(intent)
        }

        botongastos.setOnClickListener {
            val intent = Intent(this, gastos::class.java)
            startActivity(intent)
        }

        botonhistorial.setOnClickListener {
            val intent = Intent(this, historial::class.java)
            startActivity(intent)
        }

        botonsalirpantallaprincipal.setOnClickListener {
            onPause();onStop();finish();{
        }
        }
        //intentando llamar al nombre para usarlo

        val admin = AdminSQLiteHelper(this, "FinanceBank", null, 1)
        val bd = admin.readableDatabase
        var ingresos = 0
        var gastos = 0
        val data_ingresos  = bd.rawQuery("select sum(cantidad)from movimientos WHERE esIngreso = TRUE", null)
        val data_gastos  = bd.rawQuery("select sum(cantidad)from movimientos WHERE esIngreso = FALSE", null)
        if (data_ingresos.moveToFirst())
            ingresos = ingresos + data_ingresos.getInt(0)
        if (data_gastos.moveToFirst())
            gastos = gastos + data_gastos.getInt(0)
        val saldo = ingresos - gastos
        val total = "$saldo  €"
        dineroactual.setText(total)
        }
    }

