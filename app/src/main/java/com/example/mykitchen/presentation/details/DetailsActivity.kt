package com.example.mykitchen.presentation.details

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mykitchen.*
import com.example.mykitchen.domain.entity.RecipeDetails
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.android.ext.android.inject
import java.util.*

class DetailsActivity : AppCompatActivity() {
    val detailsViewModel: DetailsViewModel by inject() //Activer Koin
    private lateinit var currentRecipe : RecipeDetails
    private var favoriteRecipe : Boolean = false
    private lateinit var menu : Menu
    //private lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //actionbar
        val actionbar = supportActionBar
        //set action bar title
        actionbar!!.title = ""//No title
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        setDisplay()

        val url = intent.getStringExtra(ID_NUMBER_INTENT)
        /*detailsViewModel.getRecipeURL(id)
        detailsViewModel.recipeURL.observe(this, Observer {
            url = it
        })*/
        if(url != null){
            detailsViewModel.makeAPICall(url)
        }
        /*val handler = Handler()
        handler.postDelayed({ detailsViewModel.makeAPICall(url) }, 10000) //retrieve id of recipe*/

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
                    if (item != null) {
                        if (favoriteRecipe) {
                            println("Recipe added")
                            item.setIcon(R.drawable.ic_playlist_add_check)
                        } else {
                            println("Recipe to add")
                            item.setIcon(R.drawable.ic_playlist_add)
                        }
                    }
                }
            })
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true;
    }

    fun setDisplay(){
        titleRecipe.visibility = View.INVISIBLE
        dishTypes.visibility = View.INVISIBLE
        vegetarian.visibility = View.INVISIBLE
        vegan.visibility = View.INVISIBLE
        glutenFree.visibility = View.INVISIBLE
        dairyFree.visibility = View.INVISIBLE

        WWSmartPoints.visibility = View.INVISIBLE
        aggregatesLike.visibility = View.INVISIBLE
        spoonacularScore.visibility = View.INVISIBLE
        healthScore.visibility = View.INVISIBLE
        Ingredients.visibility = View.INVISIBLE
        Instructions.visibility = View.INVISIBLE
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
                if (favoriteRecipe) {
                    println("WANT TO REMOVE RECIPE:")
                    detailsViewModel.deleteRecipe(currentRecipe)
                    //item.setIcon(R.drawable.ic_playlist_add)
                } else {
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

        titleRecipe.visibility = View.VISIBLE
        titleRecipe.text = currentRecipe.title

        dishTypes.visibility = View.VISIBLE
        if (currentRecipe.dishTypes.isNotEmpty()){
            dishTypes.text = currentRecipe.dishTypes.toString()
        }

        vegetarian.visibility = View.VISIBLE
        vegetarian.text = "${currentRecipe.vegetarian}"
        vegan.visibility = View.VISIBLE
        vegan.text = "${currentRecipe.vegan}"
        glutenFree.visibility = View.VISIBLE
        glutenFree.text = "${currentRecipe.glutenFree}"
        dairyFree.visibility = View.VISIBLE
        dairyFree.text = "${currentRecipe.dairyFree}"

        WWSmartPoints.visibility = View.VISIBLE
        WWSmartPoints.text= "${currentRecipe.weightWatcherSmartPoints}"
        aggregatesLike.visibility = View.VISIBLE
        aggregatesLike.text = "${currentRecipe.aggregateLikes}"
        spoonacularScore.visibility = View.VISIBLE
        spoonacularScore.text= "${currentRecipe.spoonacularScore}"
        healthScore.visibility = View.VISIBLE
        healthScore.text = "${currentRecipe.healthScore}"

        //Ingredients
        displayIngredients()

        //Instructions
        displayInstructions()
    }

    private fun displayIngredients(){

        Ingredients.visibility = View.VISIBLE
        for (i in currentRecipe.extendedIngredients.indices){
            val ing = TextView(this)
            ing.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            ing.text = currentRecipe.extendedIngredients[i].name
            ingredientsLayout?.addView(ing)
        }

    }

    private fun displayInstructions(){

        Instructions.visibility = View.VISIBLE
        for(instruction in currentRecipe.analyzedInstruction[0].steps){
            val instr = TextView(this)
            instr.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            instr.text = instruction.detailStep
            instructionsLayout?.addView(instr)
        }
    }

}