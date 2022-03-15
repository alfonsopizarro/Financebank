package com.example.financebank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ingresos.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class ingresos: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresos)
        supportActionBar?.hide()

        botonsaliringresos.setOnClickListener {
            finish();{
        }
        }
    }
}