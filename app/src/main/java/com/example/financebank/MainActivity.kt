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




    }
}