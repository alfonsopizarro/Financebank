package com.example.financebank

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registroactivity.*



class registroactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registroactivity)
        getSupportActionBar()?.hide();

        val toast1 = Toast.makeText(
            applicationContext,
            "Bienvenido al área de registro", Toast.LENGTH_SHORT
        )

        toast1.show()
        botonvolver1.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
// en este fragemento de codigo 3
        //base de datos registro
        registroboton.setOnClickListener {
            val admin = AdminSQLiteHelper(this,"FinanceBank", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("name", cuadroregistronombre.getText().toString())
            registro.put("email", cuadroregistroemail.getText().toString())
            registro.put("password", cuadroregistrocontrasena.getText().toString())
            bd.insert("registro",null, registro)
            bd.close()
            Toast.makeText(this, "Enhorabuena has sido registrado correctamente", Toast.LENGTH_SHORT).show()
        //codificar contraseña buscar
        }

    }
}


