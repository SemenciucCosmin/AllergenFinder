package com.example.allergenfinder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.allergenfinder.data.datasource.dao.ProductDao
import com.example.allergenfinder.data.datasource.dao.model.AllergenEntity
import com.example.allergenfinder.data.datasource.dao.model.IngredientEntity
import com.example.allergenfinder.data.datasource.dao.model.ProductEntity

@Database(
    version = 1,
    entities = [
        ProductEntity::class,
        IngredientEntity::class,
        AllergenEntity::class,
    ],
)
internal abstract class AllergenFinderDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
