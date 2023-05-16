package com.usktea.repository

import com.usktea.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface ModuleCoreCustomerRepository : JpaRepository<Customer, Long>
