package com.example.mykitchen

import com.google.gson.annotations.SerializedName

class RecipeResponse(@SerializedName("results") val results: List<Recipe>, @SerializedName("offset") val offset: Int, @SerializedName("number") val number: Int, @SerializedName("totalResults") val totalResults: Int)