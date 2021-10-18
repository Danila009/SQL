package com.example.sql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.sql.db.MuDbManager



class MainActivity : AppCompatActivity() {

    val myDbManager = MuDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val but = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.text)
        val text_2 = findViewById<TextView>(R.id.text1)
        val but_2 = findViewById<Button>(R.id.button2)
        val result = findViewById<TextView>(R.id.textView)

        but.setOnClickListener {

            myDbManager.openDb()
            myDbManager.insertToDb(text.text.toString(), text_2.text.toString())
           
        }
        but_2.setOnClickListener {
            result.text = ""
            val dataList = myDbManager.readDbDate()
            for (item in dataList)
            {
                result.append(item)
                result.append("\n")
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

}