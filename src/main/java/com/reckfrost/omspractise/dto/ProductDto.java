package com.reckfrost.omspractise.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductDto {
    private Long id;
    private String ref;
    private String name;
    private String description;
    private Status status;
}
