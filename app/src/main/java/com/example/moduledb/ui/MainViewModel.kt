package com.example.moduledb.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.usecase.getPointsInterest
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getPointsInterest : getPointsInterest
        ) :
    ViewModel(){

 fun getPointsInterest() {
     viewModelScope.launch {
         Log.e("RESULT-TESTDB", "INVOKE")
         getPointsInterest.invoke().collect{ result ->
             Log.e("RESULT-TESTDB", result.toString())
         }
     }
 }

}