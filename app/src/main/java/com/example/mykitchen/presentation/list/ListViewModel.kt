package com.example.mykitchen.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.usecase.AddRecipeUseCase
import com.example.mykitchen.domain.usecase.GetRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel (
    private val addRecipeUseCase: AddRecipeUseCase,
    private val getRecipeUseCase: GetRecipeUseCase//Koin injection
) : ViewModel() {

    var listFavoriteRecipe: MutableLiveData<List<Recipe>> = MutableLiveData()

    fun getAllFavoriteRecipe() {
        //on passe dans un thread en background
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRecipeUseCase.getAllRecipeFromDB()
            //on se remet dans le Main thread (on est obligé lorsqu'on met a jour la vue via une LiveData
            withContext(Dispatchers.Main) {
                listFavoriteRecipe.value = response
            }
            //on se remet dans le thread en background
        }
    }
}
