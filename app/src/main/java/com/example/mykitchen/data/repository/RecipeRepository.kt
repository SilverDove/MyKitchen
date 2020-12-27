package com.example.mykitchen.data.repository

import androidx.lifecycle.LiveData
import com.example.mykitchen.API_KEY
import com.example.mykitchen.data.local.DatabaseDAO
import com.example.mykitchen.data.local.models.toData
import com.example.mykitchen.data.local.models.toEntity
import com.example.mykitchen.data.remote.RecipeApiService
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.entity.RecipeDetails

class RecipeRepository(
    private val databaseDAO: DatabaseDAO,
    private val recipeApiService: RecipeApiService
    ) {

    suspend fun addRecipe(recipe: Recipe){
        databaseDAO.insertRecipe(recipe.toData())
    }

    suspend fun getListRecipe() : List<Recipe>{
        val allRecipeDetails = databaseDAO.getAll()
        var allRecipe : List<Recipe> = listOf()
        for (i in allRecipeDetails.indices){
            allRecipe+= allRecipeDetails.get(i).toEntity()
        }
        return allRecipe
    }

    suspend fun removeRecipe(recipe: Recipe){
        databaseDAO.deleteRecipe(recipe.id)
    }

    suspend fun getRecipeWithID(recipeID: Int): Recipe?{
        return databaseDAO.getRecipe(recipeID).toEntity()
    }

    fun checkIfExists(recipeID: Int) : LiveData<Int> {
        return databaseDAO.ifExist(recipeID);
    }

    suspend fun makeRecipeAPICallURL(query: String?) : List<Recipe>{
        return  recipeApiService.getSearchResult(API_KEY, query).results
    }

    @JvmName("makeRecipeAPICall1")
    suspend fun makeRecipeAPICallURL(url: String) : RecipeDetails {
        return recipeApiService.getRecipeInformation(API_KEY, url,true)
    }

    suspend fun getRecipeURL(id: Int): String {
        return recipeApiService.getRecipeURL(id, API_KEY).sourceUrl
    }

    fun removeAll() {
        databaseDAO.deleteAllRecipe()
    }
}