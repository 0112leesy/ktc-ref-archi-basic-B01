package com.was.basic;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group_id")
    private ProductGroup productGroup;

    private String name;

    private String description;

    @Column(name = "manual_link")
    private String manualLink;

}
