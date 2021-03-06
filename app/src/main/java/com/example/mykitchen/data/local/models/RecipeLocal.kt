package com.example.mykitchen.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.entity.RecipeDetails

@Entity
data class RecipeLocal(
    @ColumnInfo(name = "recipeID") val recipeID: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image : String
){
    @PrimaryKey(autoGenerate = true) var uid: Int? = null
}

//Add recipe into the database?
fun Recipe.toData() : RecipeLocal{//Extend function to convert a Recipe into a RecipeLocal
    return RecipeLocal(
        recipeID = this.id,
        title = this.title,
        image = this.image
    )
}

fun RecipeLocal.toEntity() : Recipe{//Extend function to convert a RecipeLocal into a Recipe
       return Recipe (
           id = this.recipeID,
           title = this.title,
           image = this.image
       )
}

fun RecipeDetails.toEntity():Recipe{
    return Recipe(
        id= this.id,
        title = this.title,
        image = this.image
    )
}