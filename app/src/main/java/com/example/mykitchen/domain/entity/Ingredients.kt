package com.example.mykitchen.domain.entity

import com.google.gson.annotations.SerializedName

class Ingredients (
    @SerializedName("name") val name : String,
    @SerializedName("original") val original : String,
    @SerializedName("amount") val amount : Double,
    @SerializedName("unit") val unit : String
    )