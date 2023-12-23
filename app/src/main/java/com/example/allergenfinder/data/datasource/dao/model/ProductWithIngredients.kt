package com.example.allergenfinder.data.datasource.dao.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithIngredients(
    @Embedded val productEntity: ProductEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
        entity = AllergenEntity::class
    )
    val allergens: List<AllergenEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
        entity = IngredientEntity::class
    )
    val ingredients: List<IngredientEntity>
)
