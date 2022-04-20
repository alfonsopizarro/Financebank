package com.example.financebank

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_graficos.*
import android.graphics.Color
import android.util.Log
import com.github.mikephil.charting.data.*
import java.text.SimpleDateFormat
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.components.YAxis

import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition

import com.github.mikephil.charting.components.LimitLine








class graficos: AppCompatActivity() {

    val format = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.financebank.R.layout.activity_graficos)
        supportActionBar?.hide()

        val extras = intent.extras
        var email = ""
        var nombre = ""
        if (extras != null) {
            email = extras.getString("email").toString()
            nombre = extras.getString("nombre").toString()
        }


        val admin = AdminSQLiteHelper(this, "FinanceBank", null, 1)
        val bd = admin.readableDatabase
        var ingresos = 0
        var gastos = 0
        val data_ingresos  = bd.rawQuery("select sum(cantidad)from movimientos WHERE esIngreso = TRUE and email='${email}'", null)
        val data_gastos  = bd.rawQuery("select sum(cantidad)from movimientos WHERE esIngreso = FALSE and email='${email}'", null)
        val movimientos  = bd.rawQuery("select fecha, cantidad, esIngreso from movimientos  WHERE email='${email}' order by substr(fecha,7)||substr(fecha,4,2)||substr(fecha,1,2) asc", null)
        val notificaciones  = bd.rawQuery("select texto,cantidad from notificaciones WHERE  email='${email}'", null)


        if (data_ingresos.moveToFirst())
            //pasar datos a graficos
            ingresos = ingresos + data_ingresos.getInt(0)
        if (data_gastos.moveToFirst())
            gastos = gastos + data_gastos.getInt(0)

        create_pie_chart(ingresos, gastos)
        create_barchart(movimientos)
        create_limit_line(notificaciones)

        val toast6 = Toast.makeText(
            applicationContext,
            "Zona de graficos", Toast.LENGTH_SHORT
        )
        toast6.show()

        botonvolvergraficos.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            intent.putExtra("email",email)
            intent.putExtra("nombre", nombre)
            startActivity(intent)
        }

    }

    private fun create_limit_line(notificaciones: Cursor) {
        val leftAxis: YAxis = graficoLinea.getAxisLeft()
        leftAxis.removeAllLimitLines() // reset all limit lines to avoid overlapping lines

        while (notificaciones.moveToNext()) {
            val ll1 = LimitLine(notificaciones.getInt(1).toFloat(), notificaciones.getString(0))
            ll1.lineWidth = 4f
            ll1.enableDashedLine(10f, 10f, 0f)
            ll1.labelPosition = LimitLabelPosition.RIGHT_TOP
            ll1.textSize = 10f

            leftAxis.addLimitLine(ll1)
        }
    }


    private fun create_barchart(movimientos: Cursor) {
        val mymap = LinkedHashMap<String, Int>()

        while (movimientos.moveToNext()) {
            if (mymap.containsKey(movimientos.getString(0))) {//Ya existe la fecha en mi mapa, entonces actualizo
                val valor = mymap[movimientos.getString(0)] ?: 0
                if (movimientos.getInt(2) == 1) //esto significa que es un ingreso
                    mymap[movimientos.getString(0)] = valor + movimientos.getInt(1)
                else // es un gasto
                    mymap[movimientos.getString(0)] = valor - movimientos.getInt(1)
            } else { // No existe la fecha en mi mapa, entonces lo guardo
                if (movimientos.getInt(2) == 1) //esto significa que es un ingreso
                    mymap[movimientos.getString(0)] = movimientos.getInt(1)
                else
                    mymap[movimientos.getString(0)] = -movimientos.getInt(1)
            }
        }

        val entries =  ArrayList<Entry>()
        for((key, value) in mymap){
            Log.d("hola", key)
            Log.d("hola", value.toString())

            entries.add( Entry ((format.parse(key).time / 1000L ).toFloat(), value.toFloat()))
        }
        val dataSet =  LineDataSet(entries, "Saldo")
        val colors: ArrayList<Int> = ArrayList()
        //primero color naranja segundo verde
        colors.add(Color.parseColor("#b3543e"))
        dataSet.colors = colors

        val lineData = LineData(dataSet)
        graficoLinea.setData(lineData)
        graficoLinea.xAxis.setValueFormatter(MyXAxisValueFormatter())
        graficoLinea.invalidate()
    }

        //11
    private fun create_pie_chart(ingresos: Int, gastos: Int) {
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

        graficotarta.setData(pieData)
        graficotarta.invalidate()
    }
}
