package com.productbackendbtvn.service;

import com.productbackendbtvn.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService {
    Iterable<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    void deleteById(Long id);

    Product save(Product product);

    Optional<Product> findById(Long id);

    boolean existsByName(String name);
}
