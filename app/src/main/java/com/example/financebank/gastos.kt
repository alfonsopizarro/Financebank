package com.example.financebank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_gastos.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class gastos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gastos)
        supportActionBar?.hide()

        botonsalirgastos.setOnClickListener {
            finish();{
        }
        }
    }
}