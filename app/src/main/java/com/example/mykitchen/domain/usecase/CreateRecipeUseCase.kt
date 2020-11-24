package com.example.mykitchen.domain.usecase

import com.example.mykitchen.data.repository.RecipeRepository
import com.example.mykitchen.domain.entity.Recipe

class CreateRecipeUseCase(
    private val recipeRepository: RecipeRepository
    ) {
    suspend fun invoke(recipe : Recipe){
        recipeRepository.createRecipe(recipe)
    }
}