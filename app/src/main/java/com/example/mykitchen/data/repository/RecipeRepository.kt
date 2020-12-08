package com.example.mykitchen.data.repository

import com.example.mykitchen.API_KEY
import com.example.mykitchen.data.local.DatabaseDAO
import com.example.mykitchen.data.remote.RecipeApiService
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.entity.RecipeDetails

class RecipeRepository(
    private val databaseDAO: DatabaseDAO,
    private val recipeApiService: RecipeApiService
    ) {
    suspend fun createRecipe(recipe: Recipe){
        //API REST
        //databaseDAO.insertRecipe(recipe.toData())
    }

    suspend fun makeRecipeAPICall(query: String?) : List<Recipe>{
        val response = recipeApiService.getSearchResult(API_KEY, query)
        return response.results
    }

    suspend fun makeRecipeAPICall(idRecipe: Int) : RecipeDetails {
        return recipeApiService.getRecipeInformation(idRecipe, API_KEY)
    }

    /*fun getRecipe(id: Int) : RecipeLocal{
        val recipeLocal: RecipeLocal = databaseDAO.getRecipe(id)
        return recipeLocal.toEntity()
    }*/
}