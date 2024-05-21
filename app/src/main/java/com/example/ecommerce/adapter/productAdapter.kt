package com.example.ecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.R
import com.example.ecommerce.model.productDao

class productAdapter(
    private val productList : ArrayList<productDao>,
    private val onClick : (productDao) -> Unit
) :
    RecyclerView.Adapter<productAdapter.viewHolderClass>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)

        return viewHolderClass(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: viewHolderClass, position: Int) {
        val itemsViewModel = productList[position]
        holder.image.setImageResource(itemsViewModel.productImage)
        holder.name.text=itemsViewModel.productName
        holder.rating.rating = itemsViewModel.productRating.toFloat()
        holder.price.text = itemsViewModel.productPrice
        holder.itemView.setOnClickListener {
            onClick(itemsViewModel)
        }
    }

    class viewHolderClass(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.item_img)
        val name : TextView = itemView.findViewById(R.id.item_name)
        val rating : RatingBar = itemView.findViewById(R.id.ratingBar)
        val price : TextView = itemView.findViewById(R.id.item_price)
    }
}