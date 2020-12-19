package com.example.mykitchen.data.repository

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
        databaseDAO.deleteRecipe(recipe.toData())
    }

    suspend fun getRecipeWithID(recipeID: Int): Recipe?{
        return databaseDAO.getRecipe(recipeID).toEntity()
    }

    suspend fun makeRecipeAPICall(query: String?) : List<Recipe>{
        val response = recipeApiService.getSearchResult(API_KEY, query)
        return response.results
    }

    suspend fun makeRecipeAPICall(idRecipe: Int) : RecipeDetails {
        return recipeApiService.getRecipeInformation(idRecipe, API_KEY)
    }
}