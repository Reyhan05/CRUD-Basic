package com.reyhan.crudbasic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reyhan.crudbasic.db.Subscriber
import com.reyhan.crudbasic.repostitory.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository : SubscriberRepository): ViewModel() {

    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    // cause we use live data we dont need coroutines
    val subscribers = repository.subscriber

    private lateinit var subscriberToUpdateOrDelete : Subscriber

    private var isUpdateOrDelete = false

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value ="Update"
        clearAllOrDeleteButtonText.value = "Clear"
    }

    fun saveOrUpdate(){
        if (isUpdateOrDelete){
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.email = inputEmail.value!!
            update(subscriberToUpdateOrDelete)
        }else{
            val name= inputName.value!!
            val email = inputEmail.value!!
            insert(Subscriber(0, name, email))
            inputName.value = ""
            inputEmail.value = ""
        }
    }

    fun clearAllOrDelete(){
        if (isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        } else{
            clearAll()
        }
    }

    fun insert(subscriber: Subscriber){
        viewModelScope.launch{
            repository.insert(subscriber)
        }
    }

    fun update(subscriber: Subscriber){
        viewModelScope.launch{
            repository.update(subscriber)

            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }
    }

    fun delete(subscriber: Subscriber){
        viewModelScope.launch{
            repository.delete(subscriber)

            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }
    }

    fun clearAll(){
        viewModelScope.launch{
            repository.deleteAll()
        }
    }
}