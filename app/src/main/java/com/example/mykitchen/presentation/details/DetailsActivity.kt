package com.example.mykitchen.presentation.details

import android.os.Bundle
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

        InitialConfiguration()
    }

    fun InitialConfiguration(){
        //Get url link of the recipe
        val url = intent.getStringExtra(ID_NUMBER_INTENT)
        if(url != null){
            detailsViewModel.makeAPICall(url)
        }

        //If the details of the recipe changed, we change the display
        detailsViewModel.recipeDetails.observe(this, Observer {
            currentRecipe = it
            displayContent()//Display the information about the recipe

            //If the recipe is in the room database or not, we change the icon
            detailsViewModel.ifExist(currentRecipe.id).observe(this, {
                favoriteRecipe = it != null
                if (menu != null) run {
                    var item = menu.findItem(R.id.add_to_list)
                    if (item != null) {
                        if (favoriteRecipe) {//If the recipe is in the database
                            item.setIcon(R.drawable.ic_playlist_add_check)
                        } else {//If the recipe is not in the database
                            item.setIcon(R.drawable.ic_playlist_add)
                        }
                    }
                }
            })
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        //TODO: check if it is working
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
                if (favoriteRecipe) {//If we click on the item and the recipe is already in the database
                    detailsViewModel.deleteRecipe(currentRecipe)
                    favoriteRecipe = false
                    Toast.makeText(this, "The recipe ${currentRecipe.title} was removed from your list", Toast.LENGTH_LONG).show()
                } else {
                    favoriteRecipe = true
                    detailsViewModel.addRecipe(currentRecipe)
                    Toast.makeText(this, "The recipe ${currentRecipe.title} was added to your list", Toast.LENGTH_LONG).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayContent(){

        titleRecipe.text = currentRecipe.title

        if (currentRecipe.dishTypes.isNotEmpty()){
            dishTypes.text = currentRecipe.dishTypes.toString()
        }

        if(currentRecipe.vegetarian){
            vegetarian.setImageResource(R.drawable.vegetarian)
        }else{
            vegetarian.setImageResource(R.drawable.no_vegetarian)
        }

        if(currentRecipe.vegan){
            vegan.setImageResource(R.drawable.vegan)
        }else{
            vegan.setImageResource(R.drawable.no_vegan)
        }

        if(currentRecipe.glutenFree){
            glutenFree.setImageResource(R.drawable.gluten_free)
        }else{
            glutenFree.setImageResource(R.drawable.no_gluten_free)
        }

        if(currentRecipe.dairyFree){
            dairyFree.setImageResource(R.drawable.dairy_free)
        }else{
            dairyFree.setImageResource(R.drawable.no_dairy_free)
        }

        WWSmartPoints.text= "${currentRecipe.weightWatcherSmartPoints}"
        aggregatesLike.text = "${currentRecipe.aggregateLikes}"
        spoonacularScore.text= "${currentRecipe.spoonacularScore}"
        healthScore.text = "${currentRecipe.healthScore}"

        //Ingredients
        displayIngredients()

        //Instructions
        displayInstructions()

        sourceURL.text = currentRecipe.sourceUrl
    }

    private fun displayIngredients(){

        for (i in currentRecipe.extendedIngredients.indices){
            val ing = TextView(this)
            ing.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            ing.text = "- ${currentRecipe.extendedIngredients[i].name}      ${currentRecipe.extendedIngredients[i].amount} ${currentRecipe.extendedIngredients[i].unit}"
            ing.textSize = 18F
            ingredientsLayout?.addView(ing)
        }

    }

    private fun displayInstructions(){
        var i=1
        for(instruction in currentRecipe.analyzedInstruction[0].steps){
            val instr = TextView(this)
            instr.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            instr.text = "${i} - ${instruction.detailStep}"
            instr.textSize = 18F
            instructionsLayout?.addView(instr)
            i++
        }
    }

}