package com.example.mykitchen.domain.usecase

import androidx.lifecycle.LiveData
import com.example.mykitchen.data.repository.RecipeRepository
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.entity.RecipeDetails

class GetRecipeUseCase(
    private val recipeRepository: RecipeRepository
) {

    suspend fun getAllRecipe(query: String?): List<Recipe> {
        return recipeRepository.makeRecipeAPICallURL(query)
    }

    suspend fun getRecipeFromURL(recipeURL: String): RecipeDetails{
        return recipeRepository.makeRecipeAPICallURL(recipeURL)
    }

    suspend fun getAllRecipeFromDB(): List<Recipe> {
        return recipeRepository.getListRecipe()
    }

   fun ifRecipeExists(idRecipe: Int) : LiveData<Int>{
        return recipeRepository.checkIfExists(idRecipe)
    }

    suspend fun getRecipeFromDB(idRecipe: Int): Recipe?{
        return recipeRepository.getRecipeWithID(idRecipe)
    }

    suspend fun getRecipeURL(id: Int): String {
        return recipeRepository.getRecipeURL(id)
    }
}