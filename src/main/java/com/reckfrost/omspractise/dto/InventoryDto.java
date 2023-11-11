package com.reckfrost.omspractise.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDto {
    private Long id;
    private String ref;
    private Status status;
    private String productRef;
    private String locationRef;
    @NonNull
    private Integer quantity;
    private Integer buffer;
}
