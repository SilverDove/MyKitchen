package com.example.mykitchen.domain.entity

import com.example.mykitchen.domain.entity.Ingredients
import com.google.gson.annotations.SerializedName

class RecipeDetails(
    @SerializedName("vegetarian") val vegetarian: Boolean,
    @SerializedName("vegan") val vegan : Boolean,
    @SerializedName("glutenFree") val glutenFree: Boolean,
    @SerializedName("dairyFree") val dairyFree: Boolean,
    @SerializedName("weightWatcherSmartPoints")val weightWatcherSmartPoints : Int,
    @SerializedName("aggregateLikes") val aggregateLikes : Int,
    @SerializedName("spoonacularScore") val spoonacularScore : Int,
    @SerializedName("healthScore") val healthScore : Int,
    @SerializedName("sourceName") val sourceName : String,
    @SerializedName("pricePerServing") val pricePerServing : Double,
    @SerializedName("extendedIngredients") val extendedIngredients : List<Ingredients>,
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title: String,
    @SerializedName("servings") val servings : Int,
    @SerializedName("sourceUrl") val sourceUrl : String,
    @SerializedName("image") val image : String,
    @SerializedName("dishTypes") val dishTypes : List<String>,
    @SerializedName("analyzedInstructions") val analyzedInstruction : List<Instruction>,
    )
