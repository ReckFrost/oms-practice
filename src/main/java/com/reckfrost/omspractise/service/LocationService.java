package com.reckfrost.omspractise.service;

import com.reckfrost.omspractise.dto.LocationDto;
import com.reckfrost.omspractise.dto.Status;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LocationService {
    LocationDto createLocation(LocationDto locationDto);
    List<LocationDto> getLocations(Pageable pageable);
    LocationDto getLocationById(Long id);
    LocationDto getLocationByRef(String ref);
    LocationDto updateLocation(LocationDto locationDto);
    void deleteLocation();
    LocationDto updateLocationStatus(Long id, Status newStatus);
}
