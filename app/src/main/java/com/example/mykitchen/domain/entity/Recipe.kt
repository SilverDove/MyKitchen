package com.example.mykitchen.domain.entity

import com.google.gson.annotations.SerializedName

class Recipe (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String
    )