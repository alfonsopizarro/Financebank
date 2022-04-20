package com.example.financebank
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        val toast2 = Toast.makeText(
            applicationContext,
            "Bienvenido a Finance bank", Toast.LENGTH_SHORT
        )
        toast2.show()

        botonregistro.setOnClickListener{
            val intent= Intent(this,registroactivity::class.java)
            startActivity(intent)
        }
// en este fragemento de codigo 4

        botonLogin.setOnClickListener {
            val admin = AdminSQLiteHelper(this, "FinanceBank", null, 1)
            val bd = admin.readableDatabase
            val fila = bd.rawQuery("select name, email, password from registro where email='${loginEmail.text}' and password= '${logincontrasena.text}'", null)
            if (fila.moveToFirst()) {
            //existe algun registro como el de arriba

                val intent= Intent(this,pantallaprincipal::class.java)
                intent.putExtra("nombre",fila.getString(0))
                intent.putExtra("email",fila.getString(1))
                startActivity(intent)
            } else
                Toast.makeText(this, "Usuario o contraseña incorrecto",  Toast.LENGTH_SHORT).show()
            bd.close()
        }

        botonsalirmain.setOnClickListener{
                finish();{
        }
        }

    }
}