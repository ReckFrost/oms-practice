package com.reckfrost.omspractise.mapper;

import com.reckfrost.omspractise.dto.InventoryDto;
import com.reckfrost.omspractise.entity.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDto mapEntityToDto(Inventory inventory);
    Inventory mapDtoToEntity(InventoryDto inventoryDto);
}
