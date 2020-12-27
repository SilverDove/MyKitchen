package com.example.mykitchen.presentation.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykitchen.ID_NUMBER_INTENT
import com.example.mykitchen.MyKitchenAdapter
import com.example.mykitchen.R
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.presentation.details.DetailsActivity
import com.example.mykitchen.presentation.main.MainActivity
import com.example.mykitchen.presentation.main.MainViewModel
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recycler_view
import org.koin.android.ext.android.inject

class ListActivity : AppCompatActivity() , MyKitchenAdapter.OnItemClickListener {
    val listViewModel: ListViewModel by inject() //Activer Koin
    private lateinit var listRecipe: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        deleteAll.setOnClickListener{
            listViewModel.deleteAll()
        }

        //Si la liste change, ListActivity est prévenu pour modifier l'affichage
        listViewModel.listFavoriteRecipe.observe(this, Observer {
            listRecipe = it
            displayList()
            if(listRecipe.isEmpty()){
                listMessage.visibility = View.VISIBLE
                deleteAll.visibility = View.INVISIBLE
                listMessage.text = "You didn't add any recipe into you list ...."
            }else{
                listMessage.visibility = View.INVISIBLE
                deleteAll.visibility = View.VISIBLE
                //displayList()
            }
        })
    }

    override fun onStart() {
        initializeListRecipe()
        super.onStart()
    }


    private fun initializeListRecipe(){
        listViewModel.getAllRecipeFromDB()
    }

    private fun displayList(){
        recycler_view.adapter = MyKitchenAdapter(listRecipe, this, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)//Optimize performance when list size is fixed
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id){
            R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)}
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {//Go to another activity after clicking on the item
        val intent = Intent(this, DetailsActivity::class.java)
        var url : String
        listViewModel.getRecipeURL(listRecipe[position].id)
        listViewModel.recipeURL.observe(this, Observer {
            url = it
            intent.putExtra(ID_NUMBER_INTENT, url)
            startActivityForResult(intent,1)
        })
    }

}