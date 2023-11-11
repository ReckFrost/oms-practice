package com.reckfrost.omspractise.entity;

import com.reckfrost.omspractise.dto.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String ref;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Location location;
    @NonNull
    private Integer quantity;
    private Integer buffer;
}
