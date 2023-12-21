package com.example.moduledb.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moduledb.usecase.getPointsInterest
import com.example.moduledb.usecase.getPointsRecharge
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getPointsInterest : getPointsInterest,
    private val getPointsRecharge : getPointsRecharge
        ) :
    ViewModel(){

 fun getPointsInterest() {
     viewModelScope.launch {
         getPointsInterest.invoke().collect{ result ->
             Log.e("TEST-INVOKE-POINTS-INTEREST", result.toString())
         }
     }

     viewModelScope.launch {
         getPointsRecharge.invoke().collect{ result ->
             Log.e("TEST-INVOKE-POINTS-RECHARGE", result.toString())
         }
     }
 }

}