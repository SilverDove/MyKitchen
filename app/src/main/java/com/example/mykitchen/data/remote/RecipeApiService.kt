package com.example.mykitchen.data.remote

import androidx.lifecycle.LiveData
import com.example.mykitchen.domain.entity.RecipeDetails
import com.example.mykitchen.domain.entity.RecipeURL
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApiService {
    @GET("complexSearch")
    suspend fun getSearchResult(@Query("apiKey") apiKey: String, @Query("query") query: String?) : RecipeResponse

    @GET("{id}/information")
    suspend fun getRecipeURL(@Path("id") id: Int, @Query("apiKey") apiKey: String?) : RecipeURL

    @GET("extract")
    suspend fun getRecipeInformation(@Query("apiKey")apiKey: String?, @Query("url")url:String, @Query("analyze") analyze:Boolean): RecipeDetails
}