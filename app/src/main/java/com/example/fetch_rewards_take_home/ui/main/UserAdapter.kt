package com.example.fetch_rewards_take_home.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_rewards_take_home.R
import com.example.fetch_rewards_take_home.model.User

class UserAdapter: RecyclerView.Adapter<UserViewHolder>() {

    private val items = ArrayList<User>()
    private lateinit var user: User

    fun setItems(users: ArrayList<User>) {
        items.clear()
        items.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        user = items[position]
        holder.idTv.text = user.id.toString()
        holder.nameTv.text = user.name
    }

    override fun getItemCount(): Int = items.size
}

class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val nameTv: TextView = itemView.findViewById(R.id.name_tv)
    val idTv: TextView = itemView.findViewById(R.id.id_tv)
}