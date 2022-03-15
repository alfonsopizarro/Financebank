package com.example.financebank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_historial.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class historial: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)
        supportActionBar?.hide()

        botonsalirhistorial.setOnClickListener {
            finish();{
        }
        }
    }
}