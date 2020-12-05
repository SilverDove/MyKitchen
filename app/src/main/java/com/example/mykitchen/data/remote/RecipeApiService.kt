package com.example.mykitchen.data.remote

import com.example.mykitchen.domain.entity.RecipeDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApiService {
    @GET("complexSearch")
    suspend fun getSearchResult(@Query("apiKey") apiKey: String, @Query("query") query: String?) : RecipeResponse

    @GET("{id}/information")
    suspend fun getRecipeInformation(@Path("id") id: Int, @Query("apiKey") apiKey: String?) : RecipeDetails

}