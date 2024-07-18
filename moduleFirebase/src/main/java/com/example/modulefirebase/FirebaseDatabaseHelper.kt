package com.example.modulefirebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

internal class FirebaseDatabaseHelper(database: FirebaseDatabase) {

    private val reference: DatabaseReference = database.reference

    //function to evaluate the value of the  in the database using onSingleValueEventListener
    fun evaluateMinAppVersionAndroid(
        currentAppVersion: String,
        onNewUrl: (String) -> Unit,
        onShowUpdateDialog: (Boolean) -> Unit,
        onCancelled: (error: String) -> Unit
    ) {
        onSingleValueEventListener(
            "minAppVersionAndroid",
            onDataChanged = { snapshot ->
                if (snapshot.exists()) {
                    val url = snapshot.child("url").value.toString()
                    onNewUrl(url)
                    val minimumVersion = snapshot.child("value").value.toString()
                    if (currentAppVersion < minimumVersion) {
                        onShowUpdateDialog(true)
                    }
                } else {
                    println("Snapshot doesn't exist")
                }
            },
            onCancelled = {
                val exception = it.toException()
                exception.printStackTrace()
                onCancelled(exception.toString())
            }
        )
    }

    fun onSingleValueEventListener(
        pathString: String,
        onDataChanged: (DataSnapshot) -> Unit,
        onCancelled: (error: DatabaseError) -> Unit
    ) {
        reference.child(pathString).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onDataChanged(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                onCancelled(error)
            }
        })
    }
}