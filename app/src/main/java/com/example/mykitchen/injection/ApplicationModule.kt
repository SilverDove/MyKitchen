package com.example.mykitchen.injection

import android.content.Context
import androidx.room.Room
import com.example.mykitchen.URL_LINK
import com.example.mykitchen.data.local.AppDatabase
import com.example.mykitchen.data.local.DatabaseDAO
import com.example.mykitchen.data.remote.RecipeApiService
import com.example.mykitchen.data.repository.RecipeRepository
import com.example.mykitchen.domain.usecase.CreateRecipeUseCase
import com.example.mykitchen.domain.usecase.GetRecipeUseCase
import com.example.mykitchen.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    single { createApiRest() }
}

fun createApiRest(): RecipeApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl(URL_LINK)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(RecipeApiService::class.java)
}

fun createDatabase(context : Context) : DatabaseDAO {
    val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "MyKitcheenDatabase"
    ).build()
    return appDatabase.databaseDAO()
}
