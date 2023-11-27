package com.reckfrost.omspractise.mapper;

import com.reckfrost.omspractise.dto.InventoryDto;
import com.reckfrost.omspractise.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    @Mapping(source = "product.ref", target = "productRef")
    @Mapping(source = "location.ref", target = "locationRef")
    InventoryDto mapEntityToDto(Inventory inventory);
    Inventory mapDtoToEntity(InventoryDto inventoryDto);
}
