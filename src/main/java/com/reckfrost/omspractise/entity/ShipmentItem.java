package com.reckfrost.omspractise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shipment_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String ref;
    @ManyToOne
    private Shipment shipment;
    @ManyToOne
    private OrderItem orderItem;
    private Integer requestedQuantity;
    private Integer filledQuantity;
}
