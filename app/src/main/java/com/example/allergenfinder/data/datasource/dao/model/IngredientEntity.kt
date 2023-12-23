package com.example.allergenfinder.data.datasource.dao.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "ingredients",
    primaryKeys = ["productId"]
)
data class IngredientEntity(
    @ColumnInfo(name = "productId") val productId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "percentEstimate") val percentEstimate: Float,
    @ColumnInfo(name = "isAllergen") val isAllergen: Boolean = false,
    @ColumnInfo(name = "isMatchingAllergen") val isMatchingAllergen: Boolean = false,
)
