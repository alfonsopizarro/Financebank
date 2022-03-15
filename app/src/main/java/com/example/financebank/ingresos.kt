package com.example.financebank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ingresos: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresos)
        supportActionBar?.hide()
    }
}