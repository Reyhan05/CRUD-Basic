package com.reyhan.crudbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.reyhan.crudbasic.databinding.ActivityMainBinding
import com.reyhan.crudbasic.db.SubscribeDB
import com.reyhan.crudbasic.repostitory.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubscribeDB.getInstance(application).subscribeDao
        val repository = SubscriberRepository(dao)
        val factory = SubcriberVmFactory(repository)

        subscriberViewModel = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]

        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        displaySubscriberList()
    }
    private fun displaySubscriberList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("TAG", "displaySubscriberList: @it")
        })
    }
}