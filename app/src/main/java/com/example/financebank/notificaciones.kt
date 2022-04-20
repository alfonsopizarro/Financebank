package com.example.financebank

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gastos.*
import kotlinx.android.synthetic.main.activity_graficos.*
import kotlinx.android.synthetic.main.activity_historial.*
import kotlinx.android.synthetic.main.activity_notificaciones.*

class notificaciones: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)
        supportActionBar?.hide()

        val extras = intent.extras
        var email = ""
        var nombre = ""
        if (extras != null) {
            email = extras.getString("email").toString()
            nombre = extras.getString("nombre").toString()

        }

        botonvolvernoti.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            intent.putExtra("email",email)
            intent.putExtra("nombre", nombre)
            startActivity(intent)
        }
            //8

        val admin = AdminSQLiteHelper(this,"FinanceBank", null, 1)
        val bd = admin.writableDatabase

        botoncrearnoti.setOnClickListener{
            val notification = ContentValues()
            notification.put("texto", textonoti.getText().toString())
            notification.put("email", email)
            notification.put("cantidad", Integer.parseInt(cantidadnoti.getText().toString()))
            bd.insert("notificaciones",null, notification)
           // bd.close()
            Toast.makeText(this, "Notificacion aplicada correctamente", Toast.LENGTH_SHORT).show()

            val textoactual = mostrarnoti.text
            mostrarnoti.setText(textoactual.toString() + "\n" + "|Texto: "+ textonoti.getText().toString() +"|Cantidad: "+ Integer.parseInt(cantidadnoti.getText().toString()) +  "\n" )
        }

        botonlimpiarnoti.setOnClickListener{
            bd.delete("notificaciones",null, null)
            mostrarnoti.setText("")
        }


        val notificaciones  = bd.rawQuery("select texto,cantidad from notificaciones WHERE  email='${email}'", null)
        var text = ""
        while (notificaciones.moveToNext()) {
            text += "|Texto: "+notificaciones.getString(0)+" "+"|Cantidad: "+notificaciones.getString(1)+" \n"
        }
        mostrarnoti.setText(text)


    }


}