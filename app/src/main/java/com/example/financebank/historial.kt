package com.example.financebank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class historial: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)
        supportActionBar?.hide()
    }
}