package com.example.financebank



import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_historial.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notificaciones.*
import kotlinx.android.synthetic.main.activity_pantalla_principal.*
import kotlinx.android.synthetic.main.activity_registroactivity.*
import android.app.NotificationManager
import android.content.Context


class pantallaprincipal: AppCompatActivity() {
    lateinit var notificationManager: NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)
        supportActionBar?.hide()

        val toast3 = Toast.makeText(
            applicationContext,
            "Bienvenido a tu area de Finance bank", Toast.LENGTH_SHORT
        )
        toast3.show()

        val extras = intent.extras
        var email = ""
        if (extras != null) {
            val nombre = extras.getString("nombre")
            bienvenidonombre.setText("Bienvenido "+ nombre)
            email = extras.getString("email").toString()

        }

        botongraficos.setOnClickListener {
            val intent = Intent(this, graficos::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }

        botoningresos.setOnClickListener {
            val intent = Intent(this, ingresos::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }

        botongastos.setOnClickListener {
            val intent = Intent(this, gastos::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }
        botonotificaciones.setOnClickListener {
            val intent = Intent(this, notificaciones::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }

        botonhistorial.setOnClickListener {
            val intent = Intent(this, historial::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }

        botonsalirpantallaprincipal.setOnClickListener {
            onPause();onStop();finish();onDestroy()
        }


        val admin = AdminSQLiteHelper(this, "FinanceBank", null, 1)
        val bd = admin.readableDatabase
        val dinerototal = escribir_Dinero(bd, email)

        create_Notifications(bd, email, dinerototal)

    }

    private fun create_Notifications(
        bd: SQLiteDatabase,
        email: String,
        dinerototal: Int
    ) {
        val notificaciones =
            bd.rawQuery("select texto,cantidad from notificaciones WHERE  email='${email}'", null)
        while (notificaciones.moveToNext()) {
            if (dinerototal < notificaciones.getInt(1)) {
                var builder = NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Finance notification")
                    .setContentText(notificaciones.getString(0))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(1234, builder.build())

            }


        }
    }


    private fun escribir_Dinero(bd: SQLiteDatabase, email: String) :Int{
        var ingresos = 0
        var gastos = 0
        val data_ingresos = bd.rawQuery(
            "select sum(cantidad)from movimientos WHERE esIngreso  = TRUE and email='${email}'",
            null
        )
        val data_gastos = bd.rawQuery(
            "select sum(cantidad)from movimientos WHERE esIngreso = FALSE and email='${email}'",
            null
        )
        if (data_ingresos.moveToFirst())
            ingresos = ingresos + data_ingresos.getInt(0)
        if (data_gastos.moveToFirst())
            gastos = gastos + data_gastos.getInt(0)
        val saldo = ingresos - gastos
        val total = "$saldo  €"
        dineroactual.setText(total)
        return saldo
    }

}

