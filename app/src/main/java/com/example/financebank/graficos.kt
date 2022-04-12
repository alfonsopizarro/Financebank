package com.example.financebank

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_graficos.*
import android.graphics.Color
import com.github.mikephil.charting.data.*


class graficos: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.financebank.R.layout.activity_graficos)
        supportActionBar?.hide()

        val extras = intent.extras
        var email = ""
        if (extras != null) {
            email = extras.getString("email").toString()

        }

        val admin = AdminSQLiteHelper(this, "FinanceBank", null, 1)
        val bd = admin.readableDatabase
        var ingresos = 0
        var gastos = 0
        val data_ingresos  = bd.rawQuery("select sum(cantidad)from movimientos WHERE esIngreso = TRUE and email='${email}'", null)
        val data_gastos  = bd.rawQuery("select sum(cantidad)from movimientos WHERE esIngreso = FALSE and email='${email}'", null)
        val movimientos  = bd.rawQuery("select fecha, cantidad, esIngreso from movimientos  WHERE email='${email}' order by date(fecha) asc", null)


        if (data_ingresos.moveToFirst())
            //pasar datos a graficos
            ingresos = ingresos + data_ingresos.getInt(0)
        if (data_gastos.moveToFirst())
            gastos = gastos + data_gastos.getInt(0)

        create_pie_chart(ingresos, gastos)

        create_barchart(movimientos)


        val toast6 = Toast.makeText(
            applicationContext,
            "Zona de graficos", Toast.LENGTH_SHORT
        )
        toast6.show()

        botonvolvergraficos.setOnClickListener{
            val intent= Intent(this,pantallaprincipal::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }

    }

    private fun create_barchart(movimientos: Cursor) {
        val mymap = LinkedHashMap<String, Int>()

        while (movimientos.moveToNext()) {
            if (mymap.containsKey(movimientos.getString(0))) {//Ya existe la fecha en mi mapa, entonces actualizo
                val valor = mymap[movimientos.getString(0)] ?: 0
                if (movimientos.getInt(2) == 1) {//esto significa que es un ingreso
                    mymap[movimientos.getString(0)] = valor + movimientos.getInt(1)
                } else // es un gasto
                    mymap[movimientos.getString(0)] = valor - movimientos.getInt(1)
            } else // No existe la fecha en mi mapa, entonces lo guardo
                mymap[movimientos.getString(0)] = movimientos.getInt(1)
        }

        val entries =  ArrayList<BarEntry>()
         for((key, value) in mymap){
             entries.add(BarEntry(key, value))

         }
    }


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
