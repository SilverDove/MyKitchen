package com.example.mykitchen.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MyKitchenAdapter.OnItemClickListener {
    val mainViewModel: MainViewModel by inject() //Activer Koin
    private lateinit var ListRecipe: List<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchRecipe()

        //Si la valeur dans MainViewModel change, prévient MaintActivity (équivalent Observer)
       /* mainViewModel.test.observe(this, Observer {
            //Quand il y a des changements, je fais les opérations suivantes (généralement modifier l'affichage)
        })*/
        //Je dois prévenir la ViewModel lorsqu'il y a des changements (par exemple, un bouton a été cliqué) pour que la View s'occupe du reste

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id){
            R.id.list -> {
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)}
        }

        return super.onOptionsItemSelected(item)
    }

    private fun searchRecipe(){
        val searchBar: SearchView = findViewById(R.id.search);

        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                makeAPICall(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                makeAPICall(newText) Cela fait l'appel a chaque changement de caractère, il faut éviter car cela reset ta liste Listrecipe.
                return true
            }
        })

    }

    private fun makeAPICall(query: String?){
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RecipeApiService::class.java)

        api.getSearchResult(API_KEY, query).enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    ListRecipe = response.body()!!.results
                    if (ListRecipe.isNotEmpty()) { //If there is at least one movie
                        //display the list
                        displayList()
                    } else {
                        //No list if the result is 0
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                showError()
            }
        })
    }

    private fun showError() {
        println("API ERROR")
    }

    private fun displayList(){
        recycler_view.adapter = MyKitchenAdapter(ListRecipe, this, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)//Optimize performance when list size is fixed
    }

    override fun onItemClick(position: Int) {//Go to another activity after clicking on the item
        Toast.makeText(this, "You click the item number $position", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(ID_NUMBER_INTENT, ListRecipe.get(position).id)
        startActivityForResult(intent,1)
    }
}