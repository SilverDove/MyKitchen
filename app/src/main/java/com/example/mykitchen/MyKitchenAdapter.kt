package com.example.mykitchen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mykitchen.domain.entity.Recipe
import kotlinx.android.synthetic.main.items.view.*
import com.squareup.picasso.Picasso as Picasso1

class MyKitchenAdapter(
    private val list: List<Recipe>,
    private val listener: OnItemClickListener,
    private val context: Context
    ) : Adapter<MyKitchenAdapter.MyKitchenViewHolder>() {

    //Call by the RecyclerView when it is time to create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyKitchenViewHolder { //ViewType when we want to use several type of row layout in the recyclerview
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items,
            parent,  false)

        //return one of our MyKitchenViewHolder
        return MyKitchenViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyKitchenViewHolder, position: Int) {//What data do we put in our recyclerView? Function called each time the screen scrolls
        val currentItem = list[position]

        //Fill data depending on the index
        Picasso1.get()
                .load(currentItem.image)
                .into(holder.imageView)

        holder.title.text = currentItem.title

        holder.itemView.setOnClickListener { listener.onItemClick(position) }
    }

    //return the number of items in the list
    override fun getItemCount() = list.size

    inner class MyKitchenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Create properties storing the references to the Views on our row layout
        val imageView: ImageView = itemView.image_view //equivalent of findViewById(R.id.image_view)
        val title: TextView = itemView.title_view
    }

    interface OnItemClickListener {
        fun onItemClick(position : Int) //Function implemented by another Activity/Fragment
    }

}