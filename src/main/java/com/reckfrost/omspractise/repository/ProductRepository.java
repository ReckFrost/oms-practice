package com.reckfrost.omspractise.repository;

import com.reckfrost.omspractise.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByRef(String ref);

        Page<Product> findByNameContaining(String name, Pageable pageable);
//    Page<Product> findByNameContainingAndStatus(String name, Status status, Pageable pageable);
}
