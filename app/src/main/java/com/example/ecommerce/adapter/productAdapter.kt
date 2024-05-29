package com.example.ecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.model.category.Product
import com.example.ecommerce.model.featureProducts.Data

class productAdapter(
    private val productList : List<Data>,
    private val onClick : (Data) -> Unit,
    private val onClickAdd : (Data) -> Unit
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
        val items = productList[position]

        var rating = 0f
        if(items.ReviewOverviewModel.TotalReviews!=0)
            rating = (items.ReviewOverviewModel.RatingSum/items.ReviewOverviewModel.TotalReviews).toFloat()

        Glide.with(holder.image.context)
            .load(items.PictureModels[0].ImageUrl)
            .into(holder.image)
        holder.name.text=items.Name

        holder.rating.rating = rating
        holder.price.text = items.ProductPrice.Price
        holder.itemView.setOnClickListener {
            onClick(items)
        }
        holder.addbtn.setOnClickListener {
            onClickAdd(items)
        }
    }

    class viewHolderClass(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.item_img)
        val name : TextView = itemView.findViewById(R.id.item_name)
        val rating : RatingBar = itemView.findViewById(R.id.ratingBar)
        val price : TextView = itemView.findViewById(R.id.item_price)
        val addbtn : ImageButton = itemView.findViewById((R.id.item_img_btn))
    }
}