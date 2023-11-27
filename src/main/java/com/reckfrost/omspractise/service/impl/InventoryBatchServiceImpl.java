package com.reckfrost.omspractise.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.reckfrost.omspractise.controller.InventoryController;
import com.reckfrost.omspractise.dto.InventoryDto;
import com.reckfrost.omspractise.service.InventoryBatchService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryBatchServiceImpl implements InventoryBatchService {

    private final InventoryController inventoryController;

    public InventoryBatchServiceImpl(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }


    @Override
    public void updateBatchInventory(String batchFileDirectoryPath) {

        List<String> batchFilesPath = getBatchFiles(batchFileDirectoryPath);
        List<InventoryDto> inventoryDtoList = getInventoryData(batchFilesPath);

        inventoryDtoList.forEach(inventoryController::updateInventory);

    }

    private List<String> getBatchFiles(String batchFileDirectoryPath){
        List<String> batchFilesPath = new ArrayList<>();
        File[] filesInDirectory = new File(batchFileDirectoryPath).listFiles();

        if(ObjectUtils.isEmpty(filesInDirectory)){
            // Throw custom exception if directory is empty
        }

        for(File f : filesInDirectory){
            String filePath = f.getAbsolutePath();
            String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);
            if("csv".equals(fileExtension)){
                System.out.println("CSV file found -> " + filePath);
                batchFilesPath.add(filePath);
            }
        }
        return batchFilesPath;
    }

    private List<InventoryDto>  getInventoryData(List<String> batchFilesPath){

        List<InventoryDto> inventoryDtoList = new ArrayList<>();
        for(String batchFile : batchFilesPath) {
            try (CSVReader reader = new CSVReader(new FileReader(batchFile))) {
                // Read the header to get column names
                String[] header = reader.readNext();

                // Read data and map it to Java objects
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    Map<String, String> rowData = new HashMap<>();
                    for (int i = 0; i < header.length; i++) {
                        rowData.put(header[i], nextLine[i]);
                    }

                    // Extract data from the map
                    String productRef = rowData.get("productRef");
                    String locationRef = rowData.get("locationRef");
                    String quantity = rowData.get("quantity");

                    inventoryDtoList.add(InventoryDto.builder()
                            .productRef(productRef)
                            .locationRef(locationRef)
                            .quantity(Integer.valueOf(quantity))
                            .build());
                }
            } catch (IOException | CsvValidationException e) {
                e.fillInStackTrace();
            }
        }
        return inventoryDtoList;
    }
}
