package com.reckfrost.omspractise.repository;

import com.reckfrost.omspractise.dto.Status;
import com.reckfrost.omspractise.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByRef(String ref);
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.status = :newStatus WHERE p.id = :productId")
    void updateProductStatus(@Param("productId") Long productId, @Param("newStatus") Status newStatus);
}
