package com.example.allergenfinder.data.repository

import com.example.allergenfinder.common.toProduct
import com.example.allergenfinder.common.toProductWithIngredientsEntity
import com.example.allergenfinder.data.datasource.api.ProductApiService
import com.example.allergenfinder.data.datasource.dao.ProductDao
import com.example.allergenfinder.data.network.Resource
import com.example.allergenfinder.domain.repository.ProductRepository
import com.example.allergenfinder.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

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
        val productWithAllergens = product.copy(ingredients = ingredientsWithAllergens)
        saveProduct(productWithAllergens)
        return Resource.Success(productWithAllergens)
    }

    override suspend fun getProducts() = withContext(Dispatchers.IO) {
        productDao.getProducts().map { productEntities ->
            productEntities
                .sortedByDescending { it.productEntity.timestamp }
                .map { productEntity -> productEntity.toProduct() }
        }
    }

    private suspend fun saveProduct(product: Product) {
        withContext(Dispatchers.IO) {
            val productEntity = product.toProductWithIngredientsEntity()
            productDao.addProductWithIngredients(productEntity)
        }
    }
}
