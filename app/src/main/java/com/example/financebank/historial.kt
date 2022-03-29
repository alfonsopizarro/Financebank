package com.example.financebank

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_historial.*
import kotlinx.android.synthetic.main.activity_ingresos.*
import kotlinx.android.synthetic.main.activity_main.*

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

        botonvolverhistorial.setOnClickListener {
            val intent = Intent(this, pantallaprincipal::class.java)
            startActivity(intent)
        }
        botonprueba.setOnClickListener {
        val admin = AdminSQLiteHelper(this, "FinanceBank", null, 1)
        val bd = admin.writableDatabase
        val historiales = bd.rawQuery("select concepto, fecha, cantidad from ingresos where fecha='${fechaingresos.text}' and concepto= '${nombreingresos.text}'and cantidad= '${cantidadingresos.text}'", null)
            if (historiales.moveToFirst()) {
        historialingresos.setText("Identificador: "+historiales.getString(0)+"\n"+"nombre: "+historiales.getString(1)+"\n"+"Nº serie: "+historiales.getString(2))
        } else
        Toast.makeText(this, "No existen datos",  Toast.LENGTH_SHORT).show()
        bd.close()
    }
    }
}
