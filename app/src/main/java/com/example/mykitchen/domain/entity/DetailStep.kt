package com.example.mykitchen.domain.entity

import com.google.gson.annotations.SerializedName

class DetailStep (
    @SerializedName("step") val detailStep: String,
    @SerializedName("ingredients") val ingredients: List<Ingredients>
    )
