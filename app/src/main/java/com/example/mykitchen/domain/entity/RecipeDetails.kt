package com.example.mykitchen.domain.entity

import com.example.mykitchen.domain.entity.Ingredients
import com.google.gson.annotations.SerializedName

class RecipeDetails(
    @SerializedName("vegetarian") val vegetarian: Boolean,
    @SerializedName("vegan") val vegan : Boolean,
    @SerializedName("glutenFree") val glutenFree: Boolean,
    @SerializedName("dairyFree") val dairyFree: Boolean,
    @SerializedName("veryHealthy") val veryHealthy: Boolean,
    @SerializedName("cheap") val cheap : Boolean,
    @SerializedName("veryPopular") val veryPopular : Boolean,
    @SerializedName("sustainable") val sustainable : Boolean,
    @SerializedName("weightWatcherSmartPoints")val weightWatcherSmartPoints : Int,
    @SerializedName("lowFodmap") val lowFodmap : Boolean,
    @SerializedName("aggregateLikes") val aggregateLikes : Int,
    @SerializedName("spoonacularScore") val spoonacularScore : Int,
    @SerializedName("healthScore") val healthScore : Int,
    @SerializedName("sourceName") val sourceName : String,
    @SerializedName("pricePerServing") val pricePerServing : Double,
    @SerializedName("extendedIngredients") val extendedIngredients : List<Ingredients>,
    @SerializedName("id") val idRecipe : Int,
    @SerializedName("title") val title: String,
    @SerializedName("readyInMinutes") val readyInMinutes : Int,
    @SerializedName("servings") val servings : Int,
    @SerializedName("sourceUrl") val sourceUrl : String,
    @SerializedName("image") val image : String,
    @SerializedName("summary") val summary : String,
    @SerializedName("dishTypes") val dishTypes : List<String>,
    @SerializedName("instructions") val instructions : String,
    @SerializedName("spoonacularSourceUrl") val spoonacularSourceUrl : String
    )
