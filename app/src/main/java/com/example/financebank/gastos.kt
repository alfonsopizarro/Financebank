package com.example.financebank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class gastos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gastos)
        supportActionBar?.hide()
    }
}