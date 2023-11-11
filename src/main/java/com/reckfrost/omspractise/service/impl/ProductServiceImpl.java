package com.reckfrost.omspractise.service.impl;

import com.reckfrost.omspractise.dto.ProductDto;
import com.reckfrost.omspractise.dto.Status;
import com.reckfrost.omspractise.entity.Product;
import com.reckfrost.omspractise.mapper.ProductMapper;
import com.reckfrost.omspractise.repository.ProductRepository;
import com.reckfrost.omspractise.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.mapDtoToEntity(productDto);

        Product savedProduct = productRepository.save(product);

        return productMapper.mapEntityToDto(savedProduct);
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtoList = new ArrayList<>();
        products.forEach(product -> productDtoList.add(
                productMapper.mapEntityToDto(product)
        ));

        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product queriedProduct  = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product Not found with id: " + id));

        return productMapper.mapEntityToDto(queriedProduct);
    }

    @Override
    public ProductDto getProductByRef(String ref) {
        Product product = productRepository.findByRef(ref);

        ProductDto productDto = null;
        if(!ObjectUtils.isEmpty(product)){
            productDto = productMapper.mapEntityToDto(product);
        } else {
            throw new EntityNotFoundException("Product Not found with ref:" + ref);
        }
        // Implement else part if product not exist

        return productDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productMapper.mapDtoToEntity(productDto);

        Product updatedProduct = productRepository.save(product);

        return productMapper.mapEntityToDto(updatedProduct);
    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public ProductDto updateProductStatus(Long id, Status newStatus) {
        productRepository.updateProductStatus(id, newStatus);

        Product updatedStatusProduct = productRepository.findById(id).orElseThrow();

        return  productMapper.mapEntityToDto(updatedStatusProduct);
    }
}
