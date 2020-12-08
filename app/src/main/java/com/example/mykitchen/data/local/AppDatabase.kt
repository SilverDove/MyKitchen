package com.example.mykitchen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mykitchen.data.local.models.RecipeLocal

@Database(entities = arrayOf(RecipeLocal::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDAO(): DatabaseDAO
}
