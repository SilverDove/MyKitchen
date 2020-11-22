package com.example.mykitchen

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class details : AppCompatActivity() {

    private lateinit var currentRecipe : RecipeDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent.getIntExtra(ID_NUMBER_INTENT, 0)

        Toast.makeText(this, "The ID number of this recipe is $id", Toast.LENGTH_LONG).show()

        makeAPICall(id);

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        //Interract with database and icons
        when(item){
            //TODO: change icon -> check in database (Movie&CO)
            /*R.drawable.ic_playlist_add -> {
                //If the movie is already in the watchlist
                item.setIcon(R.drawable.ic_playlist_add_check)
            }*/
        }

        return super.onOptionsItemSelected(item)
    }

    private fun makeAPICall(id: Int){
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RecipeApiService::class.java)

        api.getRecipeInformation(id, API_KEY).enqueue(object : Callback<RecipeDetails> {
            override fun onResponse(
                call: Call<RecipeDetails>,
                response: Response<RecipeDetails>,
            ) {
                if (response.isSuccessful && response.body() != null) {
                    //currentRecipe= response.body()!!
                    System.out.println("The title is ${response.body()!!.title}")
                    //displayContent()
                } else {
                    System.out.println("It's null :(")
                    showError()
                }
            }

            override fun onFailure(call: Call<RecipeDetails>, t: Throwable) {
                showError()
            }
        })
    }

    private fun showError() {
        println("API ERROR IN DETAILS")
    }

    private fun displayContent(){
        val WWSmartPoints : TextView = findViewById(R.id.WWSmartPoints)
        val aggregatesLikes : TextView = findViewById(R.id.aggregatesLikes)
        val spoonacularScore : TextView = findViewById(R.id.spoonacularScore)
        val healthScore : TextView = findViewById(R.id.healthScore)
        val sourceName : TextView = findViewById(R.id.sourceName)
        val sourceURL : TextView = findViewById(R.id.sourceURL)
        val summary : TextView = findViewById(R.id.summary)
        val pricePerServing : TextView = findViewById(R.id.pricePerServing)
        val readyInMinuts : TextView = findViewById(R.id.readyInMinutes)
        val servings : TextView = findViewById(R.id.servings)

        WWSmartPoints.text = currentRecipe.toString()

    }


}