package com.example.mykitchen.domain.usecase

import com.example.mykitchen.data.repository.RecipeRepository
import com.example.mykitchen.domain.entity.Recipe

class AddRecipeUseCase(
    private val recipeRepository: RecipeRepository
    ) {
    suspend fun addRecipeToDB(recipe : Recipe){
        recipeRepository.addRecipe(recipe)
    }
}