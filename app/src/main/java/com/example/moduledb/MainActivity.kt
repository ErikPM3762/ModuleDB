package com.example.moduledb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moduledb.controlDB.controlInitDb.DbControl
import com.example.moduledb.controlDB.interfazAccess.initDb

class MainActivity : AppCompatActivity() {
    private val dbControlInterface: initDb = DbControl()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       dbControlInterface.initDbAhorrobus()
    }
}