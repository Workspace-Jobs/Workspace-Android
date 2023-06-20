package com.example.workspace.component

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.workspace.R
import com.example.workspace.select.offerlistActivity

class ProfileAdapter(private val context: Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    var datas = mutableListOf<Profile>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtName: TextView = itemView.findViewById(R.id.job)
        private val txtCity: TextView = itemView.findViewById(R.id.city)
        private val txtArea: TextView = itemView.findViewById(R.id.area)
        private val imgProfile: ImageView = itemView.findViewById(R.id.offer_photo)


        fun bind(item: Profile) {
            txtName.text = item.name
            txtCity.text =  item.city
            txtArea.text = item.area
            imgProfile.setImageResource(item.img)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, offerlistActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

}