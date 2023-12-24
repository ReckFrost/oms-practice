package com.reckfrost.omspractise.entity;

import com.reckfrost.omspractise.dto.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String ref;
    private String name;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
}
