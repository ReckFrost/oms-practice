package com.reckfrost.omspractise.service;

import com.reckfrost.omspractise.dto.ProductDto;
import com.reckfrost.omspractise.dto.Status;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getProducts();
    ProductDto getProductById(Long id);
    ProductDto getProductByRef(String ref);
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct();
    ProductDto updateProductStatus(Long id, Status newStatus);
}
