package com.example.allergenfinder.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.allergenfinder.data.datasource.dao.model.AllergenEntity
import com.example.allergenfinder.data.datasource.dao.model.IngredientEntity
import com.example.allergenfinder.data.datasource.dao.model.ProductEntity
import com.example.allergenfinder.data.datasource.dao.model.ProductWithIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(productEntity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIngredients(ingredientEntities: List<IngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllergens(allergenEntities: List<AllergenEntity>)

    @Query("DELETE FROM products WHERE id LIKE :productId")
    fun deleteProduct(productId: String)

    @Query("DELETE FROM ingredients WHERE productId LIKE :productId")
    fun deleteIngredients(productId: String)

    @Query("DELETE FROM allergens WHERE productId LIKE :productId")
    fun deleteAllergens(productId: String)

    @Query("SELECT * FROM products WHERE id LIKE :productId")
    fun getProduct(productId: String): ProductWithIngredients

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductWithIngredients>>

    @Transaction
    fun addProductWithIngredients(product: ProductWithIngredients) {
        deleteProductWithIngredients(product.productEntity.id)
        addIngredients(product.ingredients)
        addAllergens(product.allergens)
        addProduct(product.productEntity)
    }

    @Transaction
    fun deleteProductWithIngredients(productId: String) {
        deleteIngredients(productId)
        deleteAllergens(productId)
        deleteProduct(productId)
    }
}
