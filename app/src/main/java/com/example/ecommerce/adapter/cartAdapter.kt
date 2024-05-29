package com.example.ecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.model.cart.cartProducts.Item
import com.example.ecommerce.model.cartProduct
import com.example.ecommerce.model.featureProducts.Data

class cartAdapter(
    private val onRemoveItem : (Item) -> Unit,
    private val onUpdateItem : (Item, value : Int) -> Unit
) :
    ListAdapter<Item,cartAdapter.viewHolderClass>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cartproduct_layout,parent,false)
        return viewHolderClass(view)
    }

    override fun onBindViewHolder(holder: viewHolderClass, position: Int) {
        val item = getItem(position)

        holder.bind(item)

        holder.removeBtn.setOnClickListener{
            onRemoveItem(item)
        }

        var currVal = holder.quantity.text.toString().toInt()

        holder.addBtn.setOnClickListener {
            holder.quantity.text = (currVal+1).toString()
            onUpdateItem(item,++currVal)
        }
        holder.minusBtn.setOnClickListener {
            if(currVal==1){
                onRemoveItem(item)
            }
            else{
                holder.quantity.text = (currVal-1).toString()
                onUpdateItem(item,--currVal)
            }
        }

    }

    class viewHolderClass(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.item_img)
        val name : TextView = itemView.findViewById(R.id.item_name)
        val discount : TextView = itemView.findViewById(R.id.item_price_discount)
        val actual : TextView = itemView.findViewById(R.id.item_price_actual)
        val itemcount : TextView = itemView.findViewById(R.id.quantity_tb)
        val removeBtn : ImageView = itemView.findViewById(R.id.btn_cancel)
        val addBtn : CardView = itemView.findViewById(R.id.add_btn)
        val minusBtn : CardView = itemView.findViewById(R.id.remove_btn)
        val quantity : TextView = itemView.findViewById(R.id.quantity_tb)

        fun bind(item : Item){
            Glide.with(image.context)
                .load(item.Picture.ImageUrl)
                .into(image)
            name.text = item.ProductName
            discount.text = item.UnitPrice
            actual.visibility =  View.GONE
            itemcount.text = item.Quantity.toString()
        }
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