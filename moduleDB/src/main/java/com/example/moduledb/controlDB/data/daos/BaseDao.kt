package com.example.moduledb.controlDB.data.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import java.lang.reflect.ParameterizedType

abstract class BaseDao<E, T> {

    private val tableName = getTableName()

    /**
     * @return el ID de la fila recién insertada, o -1 si ocurrió un error
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(data: E): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(data: List<E>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdate(data: E): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdate(data: List<E>): List<Long>

    /**
     * @return Int  el número de filas actualizadas en la base de datos
     */
    @Update
    abstract suspend fun update(obj: E): Int

    @Update
    abstract suspend fun update(obj: List<E>): Int

    @Delete
    abstract suspend fun delete(data: E)

    suspend fun findAll(): List<E> {
        val query = SimpleSQLiteQuery("select * from $tableName")

        return doFindAll(query)
    }

    suspend fun findById(id: T): E? {
        val query = SimpleSQLiteQuery(
            "select * from $tableName where id = ? limit(1)",
            arrayOf(id as Any)
        )

        return doFindById(query)
    }

    suspend fun clear(): Int {
        val query = SimpleSQLiteQuery("DELETE FROM $tableName")

        return doClear(query)
    }

    suspend fun deleteById(id: String): Int {
        val query = SimpleSQLiteQuery(
            "DELETE FROM $tableName where id = ? limit(1)",
            arrayOf(id as Any)
        )

        return doDeleteById(query)
    }

    private fun getTableName(): String {
        val clazz =
            (javaClass.superclass!!.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0] as Class<*>

        return clazz.simpleName
    }

    @RawQuery
    protected abstract suspend fun doFindAll(query: SupportSQLiteQuery): List<E>

    @RawQuery
    protected abstract suspend fun doFindById(query: SupportSQLiteQuery): E

    @RawQuery
    protected abstract suspend fun doClear(query: SupportSQLiteQuery): Int

    @RawQuery
    protected abstract suspend fun doDeleteById(query: SupportSQLiteQuery): Int
}

