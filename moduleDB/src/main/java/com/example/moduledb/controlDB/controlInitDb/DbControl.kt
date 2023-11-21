package com.example.moduledb.controlDB.controlInitDb


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moduledb.controlDB.interfazAccess.initDb


class DbControl : AppCompatActivity(), initDb {

    private val createDbViewModel: ControlDbViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(ControlDbViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

  override fun initDbAhorrobus(){
       createDbViewModel.createDBAhorrobus()
   }
}