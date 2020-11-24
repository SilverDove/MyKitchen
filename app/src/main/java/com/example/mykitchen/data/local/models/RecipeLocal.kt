package com.example.mykitchen.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mykitchen.domain.entity.Recipe

@Entity
data class RecipeLocal(
    @ColumnInfo(name = "recipeID") val recipeID: Int,
    @ColumnInfo(name = "favorite") val favorite: Boolean
    /*@ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image : String?*/
){
    @PrimaryKey(autoGenerate = true) var uid: Int? = null
}

fun Recipe.toData() : RecipeLocal{//Fonction d'extension
    return RecipeLocal(
        recipeID = this.id,
        favorite = true
    )
}

/*fun RecipeLocal.toEntity() : Recipe{//Fonction d'extension
    //TODO: do API call to get and return recipe
}*/