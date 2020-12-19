package com.example.mykitchen.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mykitchen.data.local.models.RecipeLocal

@Dao
interface DatabaseDAO {
    @Query("SELECT * FROM RecipeLocal")
    fun getAll(): List<RecipeLocal>

    @Query("SELECT * FROM RecipeLocal WHERE recipeID = (:id)")
    fun getRecipe(id : Int): RecipeLocal

    @Insert
    fun insertRecipe(recipeLocal: RecipeLocal)

    @Delete
    fun deleteRecipe(recipeLocal: RecipeLocal)
}
