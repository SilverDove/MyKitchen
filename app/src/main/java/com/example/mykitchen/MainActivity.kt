package com.example.mykitchen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MyKitchenAdapter.OnItemClickListener {
    val myKitchenViewModel: MyKitchenViewModel by inject() //Activer Koin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeAPICall()
    }

    private fun makeAPICall(){
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RecipeApiService::class.java)

        api.getSearchResult(API_KEY, "pasta").enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val recipes: List<Recipe> = response.body()!!.results
                    if (recipes.isNotEmpty()) { //If there is at least one movie
                        //display the list
                        displayList(recipes)
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

    private fun displayList(recipeList : List<Recipe>){
        val exampleList: List<ItemsClass> = generateItemList(recipeList, recipeList.size)
        recycler_view.adapter = MyKitchenAdapter(exampleList, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)//Optimize performance when list size is fixed
    }

    private fun generateItemList(recipeList : List<Recipe>, size: Int): List<ItemsClass>{
        val list = ArrayList<ItemsClass>()
        //TODO: display picture

        for (i in 0 until size){
            val item = ItemsClass(DownloadImageFromPath(recipeList.get(i).image),recipeList.get(i).title, "bla bla")
            list += item
        }

        return list
    }

    override fun onItemClick(position: Int) {//Go to another activity after clicking on the item
        //TODO: Go to another activity
        Toast.makeText(this, "You click the item number $position", Toast.LENGTH_SHORT).show()
    }
}