package com.example.mykitchen.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykitchen.*
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.presentation.details.DetailsActivity
import com.example.mykitchen.presentation.list.ListActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MyKitchenAdapter.OnItemClickListener {
    val mainViewModel: MainViewModel by inject() //Activer Koin
    private lateinit var listRecipe: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noResult.text = "Use the search bar to find a recipe"

        searchRecipe()

        //Si la liste change, MainActivity est prévenue pour modifier l'affichage
        mainViewModel.listRecipe.observe(this, Observer {
            listRecipe = it
            displayList()
            if(listRecipe.isEmpty()){
                noResult.visibility = View.VISIBLE
                noResult.text = "What? No results? \nWe searched everywhere but we didn\'t find what you were looking for ☹"
            }else{
                noResult.visibility = View.INVISIBLE
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id){
            R.id.list -> {//When click on icon to go to the list activity
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)}
        }

        return super.onOptionsItemSelected(item)
    }

    private fun searchRecipe(){
        val searchBar: SearchView = findViewById(R.id.search);

        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.makeAPICall(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if( newText?.length!! > 3){
                    mainViewModel.makeAPICall(newText)
                }
                return true
            }
        })
    }

    private fun displayList(){
        recycler_view.adapter = MyKitchenAdapter(listRecipe, this, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)//Optimize performance when list size is fixed
    }

    override fun onItemClick(position: Int) {//Go to another activity after clicking on the item
        val intent = Intent(this, DetailsActivity::class.java)
        var url : String
        mainViewModel.getRecipeURL(listRecipe[position].id)
        mainViewModel.recipeURL.observe(this, Observer {
            url = it
            intent.putExtra(ID_NUMBER_INTENT, url)
            startActivityForResult(intent,1)
        })
    }
}