package com.example.moduledb.controlDB.modules

import android.content.Context
import androidx.room.Room
import com.example.moduledb.controlDB.data.local.AppDataBase
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByMacroRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbLinesByRegionDao
import com.example.moduledb.controlDB.data.local.daos.MDbLinesDetailDao
import com.example.moduledb.controlDB.data.local.daos.MDbMacroRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPOIsDao
import com.example.moduledb.controlDB.data.local.daos.MDbPORechargeDao
import com.example.moduledb.controlDB.data.local.daos.MDbRegionsDao
import com.example.moduledb.controlDB.data.local.daos.MDbRouteDao
import com.example.moduledb.controlDB.data.local.daos.MDbStopsDao
import com.example.moduledb.controlDB.data.local.daos.MDbVersionInfoDao
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
    @Singleton
    @Provides
    fun provideVersionTableDao(database: AppDataBase): MDbVersionInfoDao {
        return database.versionTable()
    }

    @Singleton
    @Provides
    fun provideMacroRegionsDao(database: AppDataBase): MDbMacroRegionsDao {
        return database.macroRegions()
    }

    @Singleton
    @Provides
    fun provideListMacroRegionsDao(database: AppDataBase): MDbLinesByMacroRegionDao {
        return database.listMacroRegions()
    }

    @Singleton
    @Provides
    fun provideRegionsDao(database: AppDataBase): MDbRegionsDao {
        return database.regions()
    }

    @Singleton
    @Provides
    fun provideListRegionsDao(database: AppDataBase): MDbLinesByRegionDao {
        return database.listRegions()
    }

    @Singleton
    @Provides
    fun provideListStopsDao(database: AppDataBase): MDbStopsDao {
        return database.listStops()
    }

    @Singleton
    @Provides
    fun provideListDetailLineDao(database: AppDataBase): MDbLinesDetailDao {
        return database.listDeatilLine()
    }

    @Singleton
    @Provides
    fun provideRoutesDao(database: AppDataBase): MDbRouteDao {
        return database.routesDao()
    }
}
