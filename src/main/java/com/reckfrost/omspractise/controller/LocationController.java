package com.reckfrost.omspractise.controller;

import com.reckfrost.omspractise.dto.LocationDto;
import com.reckfrost.omspractise.service.LocationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/omspractice/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getLocations(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<LocationDto> locations = locationService.getLocations(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(locations);
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto){

        LocationDto savedLocation = locationService.createLocation(locationDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable("id") Long id){
        LocationDto location = locationService.getLocationById(id);

        return ResponseEntity.status(HttpStatus.OK).body(location);
    }

    @GetMapping("/ref/{ref}")
    public ResponseEntity<LocationDto> getLocationByRef(@PathVariable("ref") String ref){
        LocationDto location = locationService.getLocationByRef(ref);

        return ResponseEntity.status(HttpStatus.OK).body(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable("id") Long id, @RequestBody LocationDto locationDto){
        locationDto.setId(id);
        LocationDto savedLocation = locationService.updateLocation(locationDto);

        return ResponseEntity.status(HttpStatus.OK).body(savedLocation);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LocationDto> updateLocationStatus(@PathVariable("id") Long id, @RequestBody LocationDto locationDto){
        LocationDto savedLocation = locationService.updateLocationStatus(id, locationDto.getStatus());

        return ResponseEntity.status(HttpStatus.OK).body(savedLocation);
    }
}
