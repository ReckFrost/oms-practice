package com.reckfrost.omspractise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String ref;
    @ManyToOne
    private Product product;
    private Integer quantity;
    private Double price;
    private Double taxPrice;
    private Double totalPrice;
    private Double totalTaxPrice;
    private Timestamp createdOn;
    @ManyToOne
    private Order order;
}
