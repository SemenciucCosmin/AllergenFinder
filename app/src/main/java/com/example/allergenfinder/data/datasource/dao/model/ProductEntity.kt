package com.example.allergenfinder.data.datasource.dao.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "carbohydrates") val carbohydrates: Float,
    @ColumnInfo(name = "energyKcal") val energyKcal: Float,
    @ColumnInfo(name = "fat") val fat: Float,
    @ColumnInfo(name = "fiber") val fiber: Float,
    @ColumnInfo(name = "proteins") val proteins: Float,
    @ColumnInfo(name = "salt") val salt: Float,
    @ColumnInfo(name = "saturatedFat") val saturatedFat: Float,
    @ColumnInfo(name = "sodium") val sodium: Float,
    @ColumnInfo(name = "sugars") val sugars: Float,
    @ColumnInfo(name = "nutriScoreGrade") val nutriScoreGrade: String,
    @ColumnInfo(name = "quantity") val quantity: String
)
