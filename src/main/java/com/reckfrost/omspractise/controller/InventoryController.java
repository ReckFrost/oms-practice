package com.reckfrost.omspractise.controller;

import com.reckfrost.omspractise.dto.InventoryDto;
import com.reckfrost.omspractise.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/omspractice/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getInventories(){
        List<InventoryDto> inventories = inventoryService.getInventories();

        return ResponseEntity.status(HttpStatus.OK).body(inventories);
    }

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto){

        InventoryDto savedInventory = inventoryService.createInventory(inventoryDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedInventory);
    }

    @GetMapping("{id}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable("id") Long id){
        InventoryDto inventory = inventoryService.getInventoryById(id);

        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }

    @GetMapping("/ref/{ref}")
    public ResponseEntity<InventoryDto> getInventoryByRef(@PathVariable("ref") String ref){
        InventoryDto inventory = inventoryService.getInventoryByRef(ref);

        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }

    @PutMapping("{id}")
    public ResponseEntity<InventoryDto> updateInventoryById(@PathVariable("id") Long id, @RequestBody InventoryDto inventoryDto){
        inventoryDto.setId(id);
        InventoryDto savedInventory = inventoryService.updateInventory(inventoryDto);

        return ResponseEntity.status(HttpStatus.OK).body(savedInventory);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<InventoryDto> updateInventoryStatus(@PathVariable("id") Long id, @RequestBody InventoryDto inventoryDto){
        InventoryDto savedInventory = inventoryService.updateInventoryStatus(id, inventoryDto.getStatus());

        return ResponseEntity.status(HttpStatus.OK).body(savedInventory);
    }

    @PostMapping("/update")
    public ResponseEntity<InventoryDto> updateInventory(@RequestBody InventoryDto inventoryDto){
        InventoryDto updatedInventory = inventoryService.updateInventory(inventoryDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedInventory);
    }

}
