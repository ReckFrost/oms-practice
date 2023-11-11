package com.reckfrost.omspractise.mapper;

import com.reckfrost.omspractise.dto.LocationDto;
import com.reckfrost.omspractise.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location mapDtoToEntity(LocationDto locationDto);
    LocationDto mapEntityToDto(Location location);
}
