package com.example.mykitchen.presentation.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.mykitchen.*
import com.example.mykitchen.data.remote.RecipeApiService
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.entity.RecipeDetails
import com.example.mykitchen.presentation.main.MainViewModel
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {
    val detailsViewModel: DetailsViewModel by inject() //Activer Koin
    private lateinit var currentRecipe : RecipeDetails
    private var favoriteRecipe : Boolean = false
    //private var recipeStatus: Boolean = false; //Not added into the db

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent.getIntExtra(ID_NUMBER_INTENT, 0)
        detailsViewModel.makeAPICall(id)

        /*add.setOnClickListener {
            detailsViewModel.addRecipe(currentRecipe)
        }

        remove.setOnClickListener{
            detailsViewModel.deleteRecipe(currentRecipe)
        }*/

        Toast.makeText(this, "The ID number of this recipe is $id", Toast.LENGTH_LONG).show()

        //Si la liste change, MainActivity est prévenue pour modifier l'affichage
        detailsViewModel.recipeDetails.observe(this, Observer {
            println("Hey Hey")
            currentRecipe = it
            displayContent()//change adapter is enough?
            Toast.makeText(this, "TITLE ${currentRecipe.title}", Toast.LENGTH_LONG).show()

            println("HELLO")

            detailsViewModel.ifExist(currentRecipe.idRecipe).observe(this, {
                println("WHAT SHOULD I PUT HERE? : $it")
                favoriteRecipe = it != null
            })
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.details_menu, menu)
        println("onCreateOptionsMenu")
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(favoriteRecipe){
            println("WANT TO REMOVE RECIPE:")
            detailsViewModel.deleteRecipe(currentRecipe)
            item.setIcon(R.drawable.ic_playlist_add)
        }else{
            println("WANT TO ADD RECIPE: ")
            favoriteRecipe = true
            item.setIcon(R.drawable.ic_playlist_add_check)
            detailsViewModel.addRecipe(currentRecipe)
        }
        return super.onOptionsItemSelected(item)
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

        WWSmartPoints.text = currentRecipe.weightWatcherSmartPoints.toString()
        aggregatesLikes.text = currentRecipe.aggregateLikes.toString()
        spoonacularScore.text = currentRecipe.spoonacularScore.toString()
        healthScore.text = currentRecipe.healthScore.toString()
        sourceName.text = currentRecipe.sourceName
        sourceURL.text = currentRecipe.sourceUrl
        summary.text = currentRecipe.summary
        pricePerServing.text = currentRecipe.pricePerServing.toString()
        readyInMinuts.text = currentRecipe.readyInMinutes.toString()
        servings.text = currentRecipe.servings.toString()

    }


}