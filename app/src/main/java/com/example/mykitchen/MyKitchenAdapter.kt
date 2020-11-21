package com.example.mykitchen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.items.view.*

class MyKitchenAdapter(
    private val list: List<ItemsClass>,
    private val listener: OnItemClickListener
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
        holder.imageView.setImageBitmap(currentItem.imageRessource);
        holder.title.text = currentItem.title
        holder.desc.text = currentItem.des
    }

    //return the number of items in the list
    override fun getItemCount() = list.size

    inner class MyKitchenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) ,
    View.OnClickListener{
        //Create properties storing the references to the Views on our row layout
        val imageView: ImageView = itemView.image_view //equivalent of findViewById(R.id.image_view)
        val title: TextView = itemView.title_view
        val desc: TextView = itemView.description_view

        //Initialize Item click listener
        init{
            itemView.setOnClickListener(this)
        }

        //What we do after clicking on an item
        override fun onClick(v: View?) {
            val position : Int = adapterPosition //current position

            if (position != RecyclerView.NO_POSITION){//test if position is valid
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position : Int) //Function implemented by another Activity/Fragment
    }

}