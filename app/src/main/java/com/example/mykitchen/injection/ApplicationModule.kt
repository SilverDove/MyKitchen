package com.example.mykitchen.injection

import android.content.Context
import androidx.room.Room
import com.example.mykitchen.data.local.AppDatabase
import com.example.mykitchen.data.local.DatabaseDAO
import com.example.mykitchen.data.remote.RecipeAPICall
import com.example.mykitchen.data.repository.RecipeRepository
import com.example.mykitchen.domain.usecase.CreateRecipeUseCase
import com.example.mykitchen.domain.usecase.GetRecipeUseCase
import com.example.mykitchen.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val presentationModule = module{
    //On choisit le type (single, factory etc...)
    //single : singleton i.e. garde en mémoire l'état
    factory{ MainViewModel(get(), get()) } //factory: à chaque fois que vous en demandez un, vous en crée un nouveau (create a new instance)
}

val domainModule = module {
    factory { CreateRecipeUseCase(get()) }
    factory { GetRecipeUseCase(get()) }
}

val dataModule = module{
    single { RecipeRepository(get(), get()) }//single = singleton (créé une seule fois et les valeurs des variables restent les même pendant que l'app run)
    single { createDatabase(androidContext())}
}

fun createDatabase(context : Context) : DatabaseDAO {
    val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "MyKitcheenDatabase"
    ).build()
    return appDatabase.databaseDAO()
}
