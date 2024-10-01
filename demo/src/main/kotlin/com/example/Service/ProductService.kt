package com.example.service

import com.example.entity.Product
import com.example.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun saveProduct(product: Product): Product {
        return try {
            productRepository.save(product)
        } catch (e: Exception) {
            // Handle exception or log the error
            throw RuntimeException("Failed to save product: ${e.message}")
        }
    }

    // Get all products
    fun fetchAllProducts(): List<Product?> {
        return try {
            productRepository.findAll()
        } catch (e: Exception) {
            // Handle exception or log the error
            throw RuntimeException("Failed to fetch all products: ${e.message}")
        }
    }

    // Get a product by ID
    fun fetchProductById(id: Long?): Product? {
        if (id == null) {
            return null // Or throw an exception
        }
        return try {
            productRepository.findById(id).orElse(null)
        } catch (e: Exception) {
            // Handle exception or log the error
            throw RuntimeException("Failed to fetch product by ID: ${e.message}")
        }
    }

    fun updateProduct(id: Long?, updatedProduct: Product): Product? {
        if (id == null) {
            return null // Or throw an exception
        }
        return try {
            val existingProductOptional = productRepository.findById(id)
            if (existingProductOptional.isPresent) {
                val existingProduct = existingProductOptional.get()
                // Assuming Product is a data class
                val updatedProductEntity = existingProduct.copy(
                    name = updatedProduct.name,
                    price = updatedProduct.price,
                    quantity = updatedProduct.quantity
                )
                productRepository.save(updatedProductEntity)
            } else {
                null // Or throw an exception
            }
        } catch (e: Exception) {
            // Handle exception or log the error
            throw RuntimeException("Failed to update product: ${e.message}")
        }
    }

    fun deleteProduct(id: Long?): Boolean {
        if (id == null) {
            throw IllegalArgumentException("Product ID cannot be null")
        }
        return try {
            productRepository.deleteById(id)
            true // Deletion successful
        } catch (e: Exception) {
            // Handle exception or log the error
            throw RuntimeException("Failed to delete product: ${e.message}")
        }
    }
}
