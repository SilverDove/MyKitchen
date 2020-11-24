package com.example.mykitchen.data.remote

import com.example.mykitchen.API_KEY
import com.example.mykitchen.URL_LINK
import com.example.mykitchen.domain.entity.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeAPICall() {
    fun makeAPICall(query: String?) : List<Recipe> {
        lateinit var listRecipe: List<Recipe>

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RecipeApiService::class.java)

        api.getSearchResult(API_KEY, query).enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    listRecipe = response.body()?.results?: arrayListOf()
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                showError()
            }
        })

        return listRecipe
    }

    private fun showError() {
        println("API ERROR")
    }

}