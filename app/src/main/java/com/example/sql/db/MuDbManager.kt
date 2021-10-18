package com.example.sql.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.ArrayList

class MuDbManager(context: Context) {

    val MuDbHelper = com.example.sql.db.MuDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = MuDbHelper.writableDatabase
    }

    fun insertToDb(title:String, content:String){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
        }

        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }


    fun readDbDate():ArrayList<String>{

        val dataList = ArrayList<String>()

        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null,null, null,
            null,null, null)


            while (cursor?.moveToNext()!!){
                val dataText = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        cursor.close()
        return dataList

    }

    fun closeDb(){
        MuDbHelper.close()
    }
}