package com.reckfrost.omspractise.controller;

import com.reckfrost.omspractise.dto.LocationDto;
import com.reckfrost.omspractise.service.LocationService;
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
    public ResponseEntity<List<LocationDto>> getLocations(){
        List<LocationDto> locations = locationService.getLocations();

        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto){

        LocationDto savedLocation = locationService.createLocation(locationDto);

        return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable("id") Long id){
        LocationDto location = locationService.getLocationById(id);

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping("/ref/{ref}")
    public ResponseEntity<LocationDto> getLocationByRef(@PathVariable("ref") String ref){
        LocationDto location = locationService.getLocationByRef(ref);

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable("id") Long id, @RequestBody LocationDto locationDto){
        locationDto.setId(id);
        LocationDto savedLocation = locationService.updateLocation(locationDto);

        return new ResponseEntity<>(savedLocation, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LocationDto> updateLocationStatus(@PathVariable("id") Long id, @RequestBody LocationDto locationDto){
        LocationDto savedLocation = locationService.updateLocationStatus(id, locationDto.getStatus());

        return new ResponseEntity<>(savedLocation, HttpStatus.OK);
    }
}
