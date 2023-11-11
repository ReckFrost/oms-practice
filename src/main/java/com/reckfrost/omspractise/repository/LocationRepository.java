package com.reckfrost.omspractise.repository;

import com.reckfrost.omspractise.dto.Status;
import com.reckfrost.omspractise.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location getLocationByRef(String ref);
    @Transactional
    @Modifying
    @Query("UPDATE Location l SET l.status = :newStatus WHERE l.id = :locationId")
    void updateLocationStatus(@Param("locationId") Long locationId, @Param("newStatus") Status newStatus);
}
