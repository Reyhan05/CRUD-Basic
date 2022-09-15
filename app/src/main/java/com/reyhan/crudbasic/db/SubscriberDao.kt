package com.reyhan.crudbasic.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {
    // disini kita akan akses database menggunakan room

    /*because it might lock database access on the main thread
    suspend can be paused an resumed at later time*/
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    // cause we dont need to execute this fun in background thread using coroutine
    // since this fun return LiveData, Room library di its work
    // from a background thread
    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscriber(): LiveData<List<Subscriber>>
}