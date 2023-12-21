package com.example.moduledb.controlDB

import android.content.Context
import androidx.room.Room
import com.example.moduledb.controlDB.data.AppDataBase
import com.example.moduledb.controlDB.data.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.daos.MDbPORechargeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "module_db"
        ).build()
    }

    @Singleton
    @Provides
    fun providePointsInterestDao(database: AppDataBase): MDbPOIsDao {
        return database.pointsInterest()
    }

    @Singleton
    @Provides
    fun providePointsRechargeDao(database: AppDataBase): MDbPORechargeDao {
        return database.pointsRecharge()
    }
}
