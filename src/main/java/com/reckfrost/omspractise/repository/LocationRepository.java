package com.reckfrost.omspractise.repository;

import com.reckfrost.omspractise.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> getLocationByRef(String ref);
}
