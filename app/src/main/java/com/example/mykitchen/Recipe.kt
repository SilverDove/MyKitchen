package com.example.mykitchen

import com.google.gson.annotations.SerializedName

class Recipe (@SerializedName("id") val id: Int, @SerializedName("title") val title: String, @SerializedName("image") val image: String) {
}