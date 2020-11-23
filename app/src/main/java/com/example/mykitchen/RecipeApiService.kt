package com.example.mykitchen

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApiService {
    @GET("complexSearch")
    fun getSearchResult(@Query("apiKey") apiKey: String, @Query("query") query: String?) : Call<RecipeResponse>

    @GET("{id}/information")
    fun getRecipeInformation(@Path("id") id: Int, @Query("apiKey") apiKey: String?) : Call<RecipeDetails>

}