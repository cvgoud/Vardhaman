package com.example.controller

import com.example.entity.Product
import com.example.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class ProductController(private val productService: ProductService) {

    // Create a new product
    @PostMapping("/product")
    fun saveProduct(@RequestBody product: Product): ResponseEntity<Product> {
        val savedProduct = productService.saveProduct(product)
        return ResponseEntity.ok(savedProduct)
    }

    // Get all products
    @GetMapping("/products")
    fun getAllProducts(): ResponseEntity<List<Product?>> {
        val products = productService.fetchAllProducts()
        return ResponseEntity.ok(products)
    }



    // Get a product by ID
    @GetMapping("/product/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<Product> {
        val product: Product? = productService.fetchProductById(id)
        return if (product != null) {
            ResponseEntity.ok(product)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Update a product
    @PutMapping("/product/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: Product): ResponseEntity<Product> {
        val updatedProduct: Product? = productService.updateProduct(id, product)
        return if (updatedProduct != null) {
            ResponseEntity.ok(updatedProduct)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Delete a product
    @DeleteMapping("/product/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<String> {
        return if (productService.deleteProduct(id)) {
            ResponseEntity.ok("Product with ID $id has been deleted successfully")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID $id not found")
        }
    }
}
