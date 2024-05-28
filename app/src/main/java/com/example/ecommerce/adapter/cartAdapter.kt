package com.example.ecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.model.cart.cartProducts.Item
import com.example.ecommerce.model.cartProduct
import com.example.ecommerce.model.featureProducts.Data

class cartAdapter(
    private val onRemoveItem : (Item) -> Unit
) :
    ListAdapter<Item,cartAdapter.viewHolderClass>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cartproduct_layout,parent,false)
        return viewHolderClass(view)
    }

    override fun onBindViewHolder(holder: viewHolderClass, position: Int) {
        val item = getItem(position)
        Glide.with(holder.image.context)
            .load(item.Picture.ImageUrl)
            .into(holder.image)

        holder.name.text = item.ProductName
        holder.discount.text = item.UnitPrice
        holder.actual.visibility =  View.GONE
        holder.itemcount.text = item.Quantity.toString()
        holder.removeBtn.setOnClickListener{
            onRemoveItem(item)
        }
    }

    class viewHolderClass(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.item_img)
        val name : TextView = itemView.findViewById(R.id.item_name)
        val discount : TextView = itemView.findViewById(R.id.item_price_discount)
        val actual : TextView = itemView.findViewById(R.id.item_price_actual)
        val itemcount : TextView = itemView.findViewById(R.id.quantity_tb)
        val removeBtn : ImageView = itemView.findViewById(R.id.btn_cancel)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }
}