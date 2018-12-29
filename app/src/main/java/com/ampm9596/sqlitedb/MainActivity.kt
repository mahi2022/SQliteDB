package com.ampm9596.sqlitedb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import com.ampm9596.sqlitedb.R.id.b1
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.indiview.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dBase:SQLiteDatabase= openOrCreateDatabase("mySQliteDB", Context.MODE_PRIVATE,null)

        dBase.execSQL("create table if not exists Employee(_id integer primary key autoincrement, eid integer, ename varchar(50), edesig varchar(50), edept varchar(50))")

        b1.setOnClickListener {
            var cv=ContentValues()
            cv.put("eid",em1.text.toString().toInt())
            cv.put("ename",em2.text.toString())
            cv.put("edesig",em3.text.toString())
            cv.put("edept",em4.text.toString())

            var status:Long= dBase.insert("Employee",null,cv)

            if (status!=-1L){
                Toast.makeText(this@MainActivity,"Data is Inserted",Toast.LENGTH_LONG).show()

                em1.setText("");em2.setText("")
                em3.setText("");em4.setText("")
            }else{
                Toast.makeText(this@MainActivity," DataInsertion Failed",Toast.LENGTH_LONG).show()
            }
            //    dBase.execSQL("insert into employee values(${et1.text.toString().toInt()},'${et2.text.toString()}','${et3.text.toString()}','${et4.text.toString()}')")
        } // Insert

        b2.setOnClickListener {

            var c: Cursor =dBase.query("Employee", null,null,null,null,null,null)
            var from= arrayOf("eid","ename","edesig","edept")
var to= intArrayOf(R.id.id,R.id.name,R.id.desig,R.id.dept)
          var myadapter=SimpleCursorAdapter(this@MainActivity,R.layout.indiview,c,from,to,0)
iview.adapter=myadapter
        }

    }
}
