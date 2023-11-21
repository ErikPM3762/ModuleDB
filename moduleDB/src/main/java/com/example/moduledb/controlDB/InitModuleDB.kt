package com.example.moduledb.controlDB

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.example.moduledb.controlDB.controlInitDb.DbControl

object InitModuleDB {
        private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

        @JvmStatic
        fun registerActivityResultLauncher(activityResultLauncher: ActivityResultLauncher<Intent>) {
            InitModuleDB.activityResultLauncher = activityResultLauncher
        }

        @JvmStatic
        fun initModule(context: Context, app: Int) {
            val intent = Intent(context, DbControl::class.java).apply {
                putExtra("appKey", app)
            }
            if (context is Activity) {
                activityResultLauncher.launch(intent)
            }
        }
    }