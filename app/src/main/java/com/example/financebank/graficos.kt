package com.example.financebank

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_graficos.*
import android.R
import android.graphics.Color
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import kotlinx.android.synthetic.main.activity_main.*
import com.github.mikephil.charting.data.PieData

import com.github.mikephil.charting.data.PieDataSet

import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.charts.BarChart











class graficos: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.financebank.R.layout.activity_graficos)
        supportActionBar?.hide()

        //grafico pastel
        val admin = AdminSQLiteHelper(this, "FinanceBank", null, 1)
        val bd = admin.readableDatabase
        var ingresos = 0
        var gastos = 0
        val data_ingresos  = bd.rawQuery("select sum(cantidad)from movimientos WHERE esIngreso = TRUE", null)
        val data_gastos  = bd.rawQuery("select sum(cantidad)from movimientos WHERE esIngreso = FALSE", null)
        if (data_ingresos.moveToFirst())
            ingresos = ingresos + data_ingresos.getInt(0)
        if (data_gastos.moveToFirst())
            gastos = gastos + data_gastos.getInt(0)

        val pieEntries: ArrayList<PieEntry> = ArrayList()
        val label = ""

        val typeAmountMap: MutableMap<String, Int> = HashMap()
        typeAmountMap["Ingresos"] = ingresos
        typeAmountMap["Gastos"] = gastos

        val colors: ArrayList<Int> = ArrayList()
        //primero color naranja segundo verde
        colors.add(Color.parseColor("#b3543e"));
        colors.add(Color.parseColor("#3d8f54"));

        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))
        }

        val pieDataSet = PieDataSet(pieEntries, label)
        pieDataSet.valueTextSize = 12f

        pieDataSet.colors = colors
        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(true)

        chart.setData(pieData)
        chart.invalidate()

        val toast6 = Toast.makeText(
            applicationContext,
            "Zona de graficos", Toast.LENGTH_SHORT
        )
        toast6.show()

        botonvolvergraficos.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            startActivity(intent)
        }

        }
    }
