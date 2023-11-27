package com.reckfrost.omspractise.service.impl;

import com.reckfrost.omspractise.dto.LocationDto;
import com.reckfrost.omspractise.dto.Status;
import com.reckfrost.omspractise.entity.Location;
import com.reckfrost.omspractise.exception.ResourceAlreadyExistsException;
import com.reckfrost.omspractise.exception.ResourceNotFoundException;
import com.reckfrost.omspractise.mapper.LocationMapper;
import com.reckfrost.omspractise.repository.LocationRepository;
import com.reckfrost.omspractise.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private static final String RESOURCE_NAME = "Location";

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        if(!ObjectUtils.isEmpty(locationRepository.getLocationByRef(locationDto.getRef()))){
            throw new ResourceAlreadyExistsException(RESOURCE_NAME, "ref", locationDto.getRef());
        }

        Location location = locationMapper.mapDtoToEntity(locationDto);

        Location savedLocation = locationRepository.save(location);

        return locationMapper.mapEntityToDto(savedLocation);
    }

    @Override
    public List<LocationDto> getLocations() {
        List<Location> locations = locationRepository.findAll();

        List<LocationDto> locationDtoList = new ArrayList<>();
        locations.forEach(location -> locationDtoList.add(locationMapper.mapEntityToDto(location)));

        return locationDtoList;
    }

    @Override
    public LocationDto getLocationById(Long id) {
        Location queriedLocation = locationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "id", id.toString()));

        return locationMapper.mapEntityToDto(queriedLocation);
    }

    @Override
    public LocationDto getLocationByRef(String ref) {

        Location refQueriedLocation = locationRepository.getLocationByRef(ref);

        if(ObjectUtils.isEmpty(refQueriedLocation)){
            throw new ResourceNotFoundException(RESOURCE_NAME, "ref", ref);
        }

        return locationMapper.mapEntityToDto(refQueriedLocation);
    }

    @Override
    public LocationDto updateLocation(LocationDto locationDto) {
        locationRepository.findById(locationDto.getId()).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "id", locationDto.getId().toString()));

        Location location = locationMapper.mapDtoToEntity(locationDto);

        Location updatedLocation = locationRepository.save(location);

        return locationMapper.mapEntityToDto(updatedLocation);
    }

    @Override
    public void deleteLocation() {

    }

    @Override
    public LocationDto updateLocationStatus(Long id, Status newStatus) {
        locationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "id", id.toString()));

        locationRepository.updateLocationStatus(id, newStatus);

        Location updatedStatusLocation = locationRepository.findById(id).orElseThrow();

        return  locationMapper.mapEntityToDto(updatedStatusLocation);
    }


}
