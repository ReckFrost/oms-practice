package com.reckfrost.omspractise.mapper;

import com.reckfrost.omspractise.dto.ProductDto;
import com.reckfrost.omspractise.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product mapDtoToEntity(ProductDto productDto);
    ProductDto mapEntityToDto(Product product);
}
