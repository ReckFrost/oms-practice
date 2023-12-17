package com.reckfrost.omspractise.service.impl;

import com.reckfrost.omspractise.dto.ProductDto;
import com.reckfrost.omspractise.dto.Status;
import com.reckfrost.omspractise.entity.Product;
import com.reckfrost.omspractise.exception.ResourceAlreadyExistsException;
import com.reckfrost.omspractise.exception.ResourceNotFoundException;
import com.reckfrost.omspractise.mapper.ProductMapper;
import com.reckfrost.omspractise.repository.ProductRepository;
import com.reckfrost.omspractise.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private static final String RESOURCE_NAME = "Product";

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        productRepository.findByRef(productDto.getRef())
                .map(existingProduct -> {
                    throw new ResourceAlreadyExistsException(RESOURCE_NAME, "ref", productDto.getRef());
                });

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
        Product queriedProduct  = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "id", id.toString()));

        return productMapper.mapEntityToDto(queriedProduct);
    }

    @Override
    public ProductDto getProductByRef(String ref) {
        return productRepository.findByRef(ref).map(productMapper::mapEntityToDto)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "ref", ref));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "id", productDto.getId().toString()));

        Product product = productMapper.mapDtoToEntity(productDto);

        Product updatedProduct = productRepository.save(product);

        return productMapper.mapEntityToDto(updatedProduct);
    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public ProductDto updateProductStatus(Long id, Status newStatus) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "id", id.toString()));
        if(!product.getStatus().equals(newStatus)) {
            product.setStatus(newStatus);
            product = productRepository.save(product);
        }
        return  productMapper.mapEntityToDto(product);
    }
}
