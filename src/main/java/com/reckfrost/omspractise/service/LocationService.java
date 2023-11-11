package com.reckfrost.omspractise.service;

import com.reckfrost.omspractise.dto.LocationDto;
import com.reckfrost.omspractise.dto.Status;

import java.util.List;

public interface LocationService {
    LocationDto createLocation(LocationDto locationDto);
    List<LocationDto> getLocations();
    LocationDto getLocationById(Long id);
    LocationDto getLocationByRef(String ref);
    LocationDto updateLocation(LocationDto locationDto);
    void deleteLocation();
    LocationDto updateLocationStatus(Long id, Status newStatus);
}
