package com.example.mykitchen.presentation.details

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mykitchen.*
import com.example.mykitchen.domain.entity.RecipeDetails
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject
import java.util.*

class DetailsActivity : AppCompatActivity() {
    val detailsViewModel: DetailsViewModel by inject() //Activer Koin
    private lateinit var currentRecipe : RecipeDetails
    private var favoriteRecipe : Boolean = false
    private lateinit var menu : Menu
    private lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id = intent.getIntExtra(ID_NUMBER_INTENT, 0)
        detailsViewModel.getRecipeURL(id)
        detailsViewModel.recipeURL.observe(this, Observer {
            url = it
            Toast.makeText(this, "Id is $id", Toast.LENGTH_LONG).show()
            Toast.makeText(this, "The URL link of this recipe is $url", Toast.LENGTH_LONG).show()
        })

        val handler = Handler()
        handler.postDelayed({ detailsViewModel.makeAPICall(url) }, 10000) //retrieve id of recipe
        //TODO: Display nothing until not arrived at this step

        //Si la liste change, MainActivity est prévenue pour modifier l'affichage
        detailsViewModel.recipeDetails.observe(this, Observer {
            println("Hey Hey")
            currentRecipe = it
            displayContent()//change adapter is enough?
            Toast.makeText(this, "TITLE ${currentRecipe.title}", Toast.LENGTH_LONG).show()

            println("HELLO")

            detailsViewModel.ifExist(currentRecipe.id).observe(this, {
                favoriteRecipe = it != null
                if (menu != null) run {
                    var item = menu.findItem(R.id.add_to_list)
                    if (item!= null){
                        if(favoriteRecipe){
                            println("Recipe added")
                            item.setIcon(R.drawable.ic_playlist_add_check)
                        }else{
                            println("Recipe to add")
                            item.setIcon(R.drawable.ic_playlist_add)
                        }
                    }
                }
            })
        })

        //actionbar
        val actionbar = supportActionBar
        //set action bar title
        actionbar!!.title = "Details"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true;
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (menu != null) {
            this.menu = menu
        }
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_to_list -> {
                if(favoriteRecipe){
                    println("WANT TO REMOVE RECIPE:")
                    detailsViewModel.deleteRecipe(currentRecipe)
                    //item.setIcon(R.drawable.ic_playlist_add)
                }else{
                    println("WANT TO ADD RECIPE: ")
                    favoriteRecipe = true
                    //item.setIcon(R.drawable.ic_playlist_add_check)
                    detailsViewModel.addRecipe(currentRecipe)
                }
            }
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
        //summary.text = currentRecipe.summary
        pricePerServing.text = currentRecipe.pricePerServing.toString()
        //readyInMinuts.text = currentRecipe.readyInMinutes.toString()
        servings.text = currentRecipe.servings.toString()

    }


}