package com.example.mykitchen.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.mykitchen.data.local.DatabaseDAO
import com.example.mykitchen.data.local.models.RecipeLocal
import com.example.mykitchen.data.remote.RecipeAPICall
import com.example.mykitchen.domain.entity.Recipe

class RecipeRepository(
    private val databaseDAO: DatabaseDAO,
    private val recipeAPICall: RecipeAPICall
    ) {
    suspend fun createRecipe(recipe: Recipe){
        //API REST
        //databaseDAO.insertRecipe(recipe.toData())
    }

    fun makeRecipeAPICall(query: String?) : List<Recipe>{
        return recipeAPICall.makeAPICall(query)
    }

    /*fun getRecipe(id: Int) : RecipeLocal{
        val recipeLocal: RecipeLocal = databaseDAO.getRecipe(id)
        return recipeLocal.toEntity()
    }*/
}