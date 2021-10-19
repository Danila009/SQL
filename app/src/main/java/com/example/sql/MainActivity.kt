package com.example.sql

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sql.db.MuDbHelper
import com.example.sql.db.MuDbManager
import com.example.sql.db.MyDbNameClass
import com.example.sql.db.MyDbNameClass.COLUMN_NAME_TITLE
import com.example.sql.db.MyDbNameClass.TABLE_NAME
import android.R.id
import android.app.Activity
import android.content.Intent
import android.media.Image
import android.util.Log
import android.widget.*


class MainActivity : AppCompatActivity() {

    val myDbManager = MuDbManager(this)

    var nameList = ArrayList<String>()

    val imageReguestCode = 10

    var Image:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        myDbManager.openDb()

        val but = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.text)
        val text_2 = findViewById<TextView>(R.id.text1)
        val but_2 = findViewById<Button>(R.id.button2)
        val List = findViewById<ListView>(R.id.textView)
        val but_3 = findViewById<Button>(R.id.button3)
        Image = findViewById(R.id.imageView)

        but_3.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, imageReguestCode)
        }

        but.setOnClickListener {
            when {
                text.text.toString().isNotEmpty() ->
                    myDbManager.insertToDb(text.text.toString(), text_2.text.toString(),"Test")
            }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK && requestCode == imageReguestCode){

            Image?.setImageURI(data?.data)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

}