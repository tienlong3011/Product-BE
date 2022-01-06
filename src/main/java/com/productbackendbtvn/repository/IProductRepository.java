package com.productbackendbtvn.repository;

import com.productbackendbtvn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
