package com.example.sql.db

import android.provider.BaseColumns

object MyDbNameClass: BaseColumns
{
    const val TABLE_NAME = "my_table"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"
    const val COLUMN_NAME_Image = "Image"

    const val DATABASE_VERSION = 2  //версия
    const val DATABASE_NAME = "MyLessonDb.db"  //Название

    const val CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_TITLE TEXT, $COLUMN_NAME_CONTENT TEXT, $COLUMN_NAME_Image TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}