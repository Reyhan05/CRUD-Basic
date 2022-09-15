package com.reyhan.crudbasic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reyhan.crudbasic.repostitory.SubscriberRepository
import java.lang.IllegalArgumentException

class SubcriberVmFactory(private val repository: SubscriberRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}