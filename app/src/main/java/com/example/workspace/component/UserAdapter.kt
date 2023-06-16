package com.example.workspace.component

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workspace.R
import org.w3c.dom.Text

class UserAdapter(private val context : Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var list = mutableListOf<User>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profilePic: ImageView = itemView.findViewById(R.id.profile_pic)
        val personName: TextView = itemView.findViewById(R.id.personName)
        val lastMessaged: TextView = itemView.findViewById(R.id.lastmeassed)
        val msgTime: TextView = itemView.findViewById(R.id.msgtime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cummunity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = list[position]

        holder.profilePic.setImageResource(user.imageId)
        holder.personName.text = user.name
        holder.lastMessaged.text = user.lastMessage
        holder.msgTime.text = user.lastMsgtime
    }

    override fun getItemCount(): Int {
        return list.size
    }
}