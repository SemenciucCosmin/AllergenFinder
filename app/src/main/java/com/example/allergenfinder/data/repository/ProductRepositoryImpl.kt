package com.example.allergenfinder.data.repository

import com.example.allergenfinder.common.toProduct
import com.example.allergenfinder.common.toProductWithIngredientsEntity
import com.example.allergenfinder.data.datasource.api.ProductApiService
import com.example.allergenfinder.data.datasource.dao.ProductDao
import com.example.allergenfinder.data.network.Resource
import com.example.allergenfinder.domain.repository.ProductRepository
import com.example.allergenfinder.model.Product

val allAllergens = listOf(
    "peanuts",
    "tree nuts",
    "wheat",
    "barley",
    "rye",
    "oats",
    "spelt",
    "milk",
    "eggs",
    "fish",
    "shellfish",
    "mustard",
    "sesame",
    "soybeans",
    "lupin",
    "sulphur dioxide",
    "sulphites",
    "celery",
    "crustacean shellfish",
    "molluscs"
)

val userAllergens = listOf("vanillin")

class ProductRepositoryImpl(
    private val productApiService: ProductApiService,
    private val productDao: ProductDao,
) : ProductRepository {
    override suspend fun fetchProduct(barcode: String): Resource<Product> {
        val apiResource = productApiService.getProductInfo(barcode)
        if (apiResource is Resource.Error) return apiResource.getErrorType()
        val product = apiResource.getOrNull()?.toProduct() ?: return Resource.Error.NotFound()
        val ingredientsWithAllergens = product.ingredients.map { ingredient ->
            ingredient.copy(
                isAllergen = allAllergens.contains(ingredient.name) ||
                        product.allergens.contains(ingredient.name),
                isMatchingAllergen = userAllergens.contains(ingredient.name)
            )
        }
        val newProduct = product.copy(ingredients = ingredientsWithAllergens)
        val productEntity = newProduct.toProductWithIngredientsEntity()
        return Resource.Success(newProduct)
    }
}
