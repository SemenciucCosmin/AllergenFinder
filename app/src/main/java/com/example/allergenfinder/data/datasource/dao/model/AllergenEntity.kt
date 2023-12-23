package com.example.allergenfinder.data.datasource.dao.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "allergens",
    primaryKeys = ["productId"]
)
data class AllergenEntity(
    @ColumnInfo(name = "productId") val productId: String,
    @ColumnInfo(name = "name") val name: String
)
