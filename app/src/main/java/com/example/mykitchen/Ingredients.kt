package com.example.mykitchen

import com.google.gson.annotations.SerializedName

class Ingredients (@SerializedName("id") val idIngredients : Int, @SerializedName("name") val name : String, @SerializedName("original") val original : String, @SerializedName("amount") val amount : Double, @SerializedName("unit") val unit : String ){
}