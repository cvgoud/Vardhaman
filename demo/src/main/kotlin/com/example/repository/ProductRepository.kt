package com.example.repository

import com.example.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

    @Repository
    interface ProductRepository : JpaRepository<Product?, Long>{}
