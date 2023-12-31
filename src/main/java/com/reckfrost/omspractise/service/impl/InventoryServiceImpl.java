package com.reckfrost.omspractise.service.impl;

import com.reckfrost.omspractise.dto.InventoryDto;
import com.reckfrost.omspractise.dto.Status;
import com.reckfrost.omspractise.entity.Inventory;
import com.reckfrost.omspractise.entity.Location;
import com.reckfrost.omspractise.entity.Product;
import com.reckfrost.omspractise.mapper.InventoryMapper;
import com.reckfrost.omspractise.repository.InventoryRepository;
import com.reckfrost.omspractise.repository.LocationRepository;
import com.reckfrost.omspractise.repository.ProductRepository;
import com.reckfrost.omspractise.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    final InventoryRepository inventoryRepository;
    final InventoryMapper inventoryMapper;
    final ProductRepository productRepository;
    final LocationRepository locationRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper, ProductRepository productRepository, LocationRepository locationRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        inventoryDto.setRef(inventoryDto.getProductRef() + ":" + inventoryDto.getLocationRef());

        Inventory inventory = inventoryMapper.mapDtoToEntity(inventoryDto);

        Product product = productRepository.findByRef(inventoryDto.getProductRef());
        Location location = locationRepository.getLocationByRef(inventoryDto.getLocationRef());

        inventory.setProduct(product);
        inventory.setLocation(location);

        Inventory savedInventory = inventoryRepository.save(inventory);

        return inventoryMapper.mapEntityToDto(savedInventory);
    }

    @Override
    public List<InventoryDto> getInventories() {

        List<Inventory> inventories = inventoryRepository.findAll();

        List<InventoryDto> inventoryDtos = new ArrayList<>();

        inventories.forEach(inventory -> inventoryDtos.add(inventoryMapper.mapEntityToDto(inventory)));

        return inventoryDtos;
    }

    @Override
    public InventoryDto getInventoryById(Long id) {

        Inventory queriedInventory = inventoryRepository.findById(id).orElseThrow();

        InventoryDto inventoryDto = inventoryMapper.mapEntityToDto(queriedInventory);
        inventoryDto.setProductRef(queriedInventory.getProduct().getRef());
        inventoryDto.setLocationRef(queriedInventory.getLocation().getRef());

        return inventoryDto;
    }

    @Override
    public InventoryDto getInventoryByRef(String ref) {
        Inventory inventory = inventoryRepository.getInventoryByRef(ref);

        return inventoryMapper.mapEntityToDto(inventory);
    }


    @Override
    public InventoryDto updateInventory(InventoryDto inventoryDto) {
        Inventory inventoryToBeUpdated = inventoryRepository.findById(inventoryDto.getId()).orElseThrow();

        inventoryToBeUpdated.setQuantity(inventoryDto.getQuantity());
        if(inventoryDto.getBuffer() != null) inventoryToBeUpdated.setBuffer(inventoryDto.getBuffer());

        Inventory updatedInventory = inventoryRepository.save(inventoryToBeUpdated);

        return inventoryMapper.mapEntityToDto(updatedInventory);
    }

    @Override
    public InventoryDto updateInventoryStatus(Long id, Status status) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow();

        if(!inventory.getStatus().equals(status)){
            inventory.setStatus(status);
            inventoryRepository.updateInventoryStatus(id, status);
        }

        return inventoryMapper.mapEntityToDto(inventory);
    }

}
