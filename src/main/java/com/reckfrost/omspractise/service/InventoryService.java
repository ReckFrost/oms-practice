package com.reckfrost.omspractise.service;

import com.reckfrost.omspractise.dto.InventoryDto;
import com.reckfrost.omspractise.dto.Status;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto);
    List<InventoryDto> getInventories();
    InventoryDto getInventoryById(Long id);
    InventoryDto getInventoryByRef(String ref);
    InventoryDto updateInventory(InventoryDto inventoryDto);
    InventoryDto updateInventoryById(InventoryDto inventoryDto);
    InventoryDto updateInventoryStatus(Long id, Status status);
}
