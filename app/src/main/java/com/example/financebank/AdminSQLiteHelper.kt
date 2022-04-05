package com.example.financebank
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory

class AdminSQLiteHelper (context: Context, name: String, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(" CREATE TABLE " + "registro" + " (" +
                "name" + " TEXT NOT NULL, " +
                "email" + " TEXT PRIMARY KEY, " +
                "password" + " TEXT NOT NULL);"
        );
        db.execSQL(" CREATE TABLE " + "movimientos" + " (" +
                "email" + " TEXT NOT NULL, " +
                "concepto" + " TEXT NOT NULL, " +
                "fecha" + " TEXT NOT NULL, " +
                "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "esIngreso" + " BOOLEAN NOT NULL, " +
                "cantidad" + " Int NOT NULL);"
        );
        db.execSQL(" CREATE TABLE " + "notificaciones" + " (" +
                "email" + " TEXT NOT NULL, " +
                "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "texto" + " TEXT NOT NULL, " +
                "cantidad" + " Int NOT NULL);"
        );

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {


    }
}
