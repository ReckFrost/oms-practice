package com.reckfrost.omspractise.controller;

import com.reckfrost.omspractise.dto.ProductDto;
import com.reckfrost.omspractise.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/omspractice/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id){
        ProductDto product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/ref/{ref}")
    public ResponseEntity<ProductDto> getProductByRef(@PathVariable("ref") String ref){
        ProductDto product = productService.getProductByRef(ref);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){

        ProductDto savedProduct = productService.createProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        productDto.setId(id);
        ProductDto savedProduct = productService.updateProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ProductDto> updateProductStatus(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        ProductDto savedProduct = productService.updateProductStatus(id, productDto.getStatus());

        return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
    }
}
