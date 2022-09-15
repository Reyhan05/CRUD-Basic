package com.reyhan.crudbasic.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// to represent the actual database
@Database(entities = [Subscriber::class], version = 1)
abstract class SubscribeDB : RoomDatabase() {

    abstract val subscribeDao : SubscriberDao

     /*we should only use one interface of room
     database for entire app
     to avoid unexpected errors*/

    // we create singleton as companion object
    companion object {
        // this reference to the subsriberDatabase
        @Volatile
        // make field immediately made visible to ohter thread
        private var INSTACE : SubscribeDB? = null

        fun getInstance(context: Context) : SubscribeDB {
            // in this blok we add synchronized block
            // lock to represent SubscriberDB
            synchronized(this){
                var instance = INSTACE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscribeDB::class.java,
                        "subscriber_data.db"
                    ).build()
                }
                return instance
            }
        }
    }

}