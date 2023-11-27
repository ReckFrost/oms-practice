package com.reckfrost.omspractise.scheduler;

import com.reckfrost.omspractise.service.InventoryBatchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InventoryBatchScheduler {

    private final InventoryBatchService inventoryBatchService;

    public InventoryBatchScheduler(InventoryBatchService inventoryBatchService) {
        this.inventoryBatchService = inventoryBatchService;
    }

    @Scheduled(fixedRate = 15000)
    public void loadInventoryBatch(){
        String batchFilesDirectoryLocation = "/home/reckfrost/Workspace/oms-practice-docs";

        inventoryBatchService.updateBatchInventory(batchFilesDirectoryLocation);
    }
}
