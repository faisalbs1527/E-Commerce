package com.example.ecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.model.category.Data

class categoryAdapter(
    private val categoryList : List<Data>,
    private val onClick : (Data) -> Unit
) : RecyclerView.Adapter<categoryAdapter.viewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)

        return viewHolderClass(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: viewHolderClass, position: Int) {
        val items = categoryList[position]
        holder.name.text= items.Name

        Glide.with(holder.image.context)
            .load(items.Products[0].PictureModels[0].ImageUrl)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            onClick(items)
        }
    }

    class viewHolderClass(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.category_img)
        val name : TextView = itemView.findViewById(R.id.category_name)
    }
}