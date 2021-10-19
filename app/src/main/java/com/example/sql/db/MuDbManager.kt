package com.example.sql.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import org.w3c.dom.Text
import java.lang.Long.getLong
import java.util.ArrayList

class MuDbManager(context: Context) {

    val MuDbHelper = MuDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = MuDbHelper.writableDatabase
    }

    fun insertToDb(title:String, content:String, Image:String){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDbNameClass.COLUMN_NAME_Image, Image)
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

    fun Upgrade(): ArrayList<String> {

        val new = ArrayList<String>()

        val title = "Name"
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
        }

        val selection = "${MyDbNameClass.COLUMN_NAME_TITLE} LIKE ?"
        val selectionArgs = arrayOf("NEW_NAME")
        val count = db?.update(
            MyDbNameClass.TABLE_NAME,
            values,
            selection,
            selectionArgs)

        new.add(count.toString())
        return new
    }


    fun closeDb(){
        MuDbHelper.close()
    }
}