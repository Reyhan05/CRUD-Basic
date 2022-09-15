package com.reyhan.crudbasic.repostitory

import com.reyhan.crudbasic.db.Subscriber
import com.reyhan.crudbasic.db.SubscriberDao

class SubscriberRepository(private val dao: SubscriberDao) {

    val subscriber = dao.getAllSubscriber()

    suspend fun insert(subscriber: Subscriber){
        dao.insertSubscriber(subscriber)
    }

    suspend fun  update(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }

    suspend fun  delete(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}