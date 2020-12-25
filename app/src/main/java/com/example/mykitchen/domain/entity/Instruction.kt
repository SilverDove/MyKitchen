package com.example.mykitchen.domain.entity

import com.google.gson.annotations.SerializedName

class Instruction (
    @SerializedName("steps") val steps: List<DetailStep>
    )
