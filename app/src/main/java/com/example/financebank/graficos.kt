package com.example.financebank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_graficos.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*

class graficos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graficos)
        supportActionBar?.hide()

        botonsalirgraficos.setOnClickListener {
            finish();{
        }
        }
    }
}