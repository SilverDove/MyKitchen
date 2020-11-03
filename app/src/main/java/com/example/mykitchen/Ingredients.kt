package com.example.mykitchen

import com.google.gson.annotations.SerializedName

class Ingredients (@SerializedName("id") val id : Int,  @SerializedName("image") val image : String, @SerializedName("name") val name : String, @SerializedName("original") val original : String, @SerializedName("amount") val amount : Int, @SerializedName("unit") val unit : String ){
}