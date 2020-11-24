package com.example.mykitchen.injection

import com.example.mykitchen.data.repository.RecipeRepository
import com.example.mykitchen.domain.usecase.CreateRecipeUseCase
import com.example.mykitchen.presentation.main.MainViewModel
import org.koin.dsl.module

val presentationModule = module{
    //On choisit le type (single, factory etc...)
    //single : singleton i.e. garde en mémoire l'état
    factory{ MainViewModel(get()) } //factory: à chaque fois que vous en demandez un, vous en crée un nouveau (create a new instance)
}

val domainModule = module {
    factory { CreateRecipeUseCase(get()) }
}

val dataModule = module{
    single { RecipeRepository() }//single = singleton (créé une seule fois et les valeurs des variables restent les même pendant que l'app run)
}