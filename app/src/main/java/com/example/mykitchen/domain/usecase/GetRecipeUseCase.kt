package com.example.mykitchen.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mykitchen.data.repository.RecipeRepository
import com.example.mykitchen.domain.entity.Recipe

class GetRecipeUseCase(
    private val recipeRepository: RecipeRepository
) {
    suspend fun invoke(id : Int){
        //recipeRepository.getRecipe(id)
    }

    fun getAllRecipe(query: String?): List<Recipe> {
        return recipeRepository.makeRecipeAPICall(query)
    }
}