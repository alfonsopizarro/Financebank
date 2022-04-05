package com.example.financebank

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_historial.*
import kotlinx.android.synthetic.main.activity_ingresos.*

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

        val extras = intent.extras
        var email = ""
        if (extras != null) {
            email = extras.getString("email").toString()

        }

        botonvolverhistorial.setOnClickListener {
            val intent = Intent(this, pantallaprincipal::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }

        val admin = AdminSQLiteHelper(this, "FinanceBank", null, 1)
        val bd = admin.writableDatabase
        val historiales = bd.rawQuery("select concepto, fecha, cantidad, esIngreso from movimientos WHERE email='${email}'", null)

        var text = ""
        while (historiales.moveToNext()) {
            val signo = if( historiales.getInt(3) == 1)  "+" else "-"
            text += "|Fecha: "+historiales.getString(1)+" "+"|Concepto: "+historiales.getString(0)+" "+"|Cantidad: "+ signo +historiales.getString(2) +"€ \n"
        }
        historialmovimientos.setText(text)
        if (text == "")
            historialmovimientos.setText("No hay datos, intruducelos en Ingresos o Gastos.")
        bd.close()

    }
}

