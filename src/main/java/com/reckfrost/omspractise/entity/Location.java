package com.reckfrost.omspractise.entity;

import com.reckfrost.omspractise.dto.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "location")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String ref;
    private String name;
    @Column(length = 350)
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
}
