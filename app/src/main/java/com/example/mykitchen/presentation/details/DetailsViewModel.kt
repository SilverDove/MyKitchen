package com.example.mykitchen.presentation.details

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykitchen.data.local.models.toEntity
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.entity.RecipeDetails
import com.example.mykitchen.domain.usecase.AddRecipeUseCase
import com.example.mykitchen.domain.usecase.GetRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel (
    private val addRecipeUseCase: AddRecipeUseCase,
    private val getRecipeUseCase: GetRecipeUseCase//Koin injection
) : ViewModel(){

    var recipeDetails: MutableLiveData<RecipeDetails> = MutableLiveData()

    fun makeAPICall(idRecipe: Int) {
        //on passe dans un thread en background
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRecipeUseCase.getRecipeFromID(idRecipe)
            //on se remet dans le Main thread (on est obligé lorsqu'on met a jour la vue via une LiveData
            withContext(Dispatchers.Main){
                recipeDetails.value = response
            }
            //on se remet dans le thread en background
        }
    }

    fun addRecipe(recipe: RecipeDetails){
        viewModelScope.launch(Dispatchers.IO) {
            addRecipeUseCase.addRecipeToDB(recipe.toEntity())
            System.out.println("ADDED ELEMENT INTO THE DATABASE. HERE IS THE LIST OF RECIPIES IN THE DATABASE:")
            val list = getRecipeUseCase.getAllRecipeFromDB()
            for (r in list){
                System.out.println(r.title)
            }
        }
    }

    fun deleteRecipe(recipe: RecipeDetails){
        viewModelScope.launch(Dispatchers.IO) {
            addRecipeUseCase.removeRecipeFromDB(recipe.toEntity())
            System.out.println("REMOVED ELEMENT INTO THE DATABASE. HERE IS THE LIST OF RECIPIES IN THE DATABASE:")
            val list = getRecipeUseCase.getAllRecipeFromDB()
            for (r in list){
                System.out.println(r.title)
            }
        }
    }

    fun ifExist(idRecipe: Int): Recipe? {
        var recipe : Recipe? = null
        viewModelScope.launch(Dispatchers.IO) {
            recipe = getRecipeUseCase.getRecipeFromDB(idRecipe)
        }
        return recipe
    }
}