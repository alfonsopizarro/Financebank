package com.example.financebank

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_despues_notificaciones.*
import kotlinx.android.synthetic.main.activity_graficos.*

class mensajenotifiacion: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_despues_notificaciones)
        supportActionBar?.hide()

    }
}
