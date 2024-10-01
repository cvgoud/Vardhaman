package com.example.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Double,

    @Column(nullable = false)
    val quantity: Int
) {
    // You can add any additional methods or override functions here if needed
}
