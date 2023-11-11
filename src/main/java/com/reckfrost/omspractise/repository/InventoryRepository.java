package com.reckfrost.omspractise.repository;

import com.reckfrost.omspractise.dto.Status;
import com.reckfrost.omspractise.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory getInventoryByRef(String ref);
    @Transactional
    @Modifying
    @Query("UPDATE Inventory i SET i.status = :newStatus WHERE i.id = :inventoryId")
    void updateInventoryStatus(@Param("inventoryId") Long inventoryId, @Param("newStatus") Status newStatus);
}
