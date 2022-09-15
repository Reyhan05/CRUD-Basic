package com.reyhan.crudbasic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.reyhan.crudbasic.R
import com.reyhan.crudbasic.databinding.ItemListBinding
import com.reyhan.crudbasic.db.Subscriber

class MyRecyclerViewAdapter(
    private val subscriberList: List<Subscriber>,
    private val clickListener: (Subscriber) -> Unit
)
    : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemListBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_list, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position], clickListener)
    }

    override fun getItemCount(): Int = subscriberList.size

}

class MyViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }
}