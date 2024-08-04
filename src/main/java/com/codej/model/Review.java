package com.codej.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String review;

    @OneToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
