package com.example.mykitchen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    val myKitchenViewModel: MyKitchenViewModel by inject() //Activer Koin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exampleList = generateDummyList(500)

        recycler_view.adapter = MyKitchenAdapter(exampleList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)//Optimize performance when list size is fixed

    }

    private fun generateDummyList(size: Int): List<ItemsClass>{
        val list = ArrayList<ItemsClass>()

        for (i in 0 until size){
            val drawable = when (i%3){
                0 -> R.drawable.ic_launcher_background
                1 -> R.drawable.ic_launcher_foreground
                else -> R.drawable.ic_launcher_background
            }
            val item = ItemsClass(drawable, "Titel $i", "Description")
            list += item
        }

        return list
    }
}