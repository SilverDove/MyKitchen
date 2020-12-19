package com.example.mykitchen.presentation.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class ListActivity : AppCompatActivity() , MyKitchenAdapter.OnItemClickListener {
    val listViewModel: ListViewModel by inject() //Activer Koin
    private lateinit var listRecipe: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initializeListRecipe()

        //Si la liste change, ListActivity est prévenu pour modifier l'affichage
        listViewModel.listFavoriteRecipe.observe(this, Observer {
            listRecipe = it
            displayList()
        })
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
        Toast.makeText(this, "You click the item number $position", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(ID_NUMBER_INTENT, listRecipe.get(position).id)
        startActivityForResult(intent,1)
    }

}