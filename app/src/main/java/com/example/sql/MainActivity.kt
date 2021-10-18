package com.example.sql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.sql.db.MuDbManager



class MainActivity : AppCompatActivity() {

    val myDbManager = MuDbManager(this)

    var nameList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val but = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.text)
        val text_2 = findViewById<TextView>(R.id.text1)
        val but_2 = findViewById<Button>(R.id.button2)
        val List = findViewById<ListView>(R.id.textView)

        but.setOnClickListener {

            myDbManager.openDb()
            myDbManager.insertToDb(text.text.toString(), text_2.text.toString())
           
        }
        but_2.setOnClickListener {

            val dataList = myDbManager.readDbDate()
            for (item in dataList)
            {
                nameList.add("$item \n")

                List.adapter = ArrayAdapter(
                    this,
                    R.layout.text_color_white, nameList
                )
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

}