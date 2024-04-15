package com.was.basic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
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

    @Column(name = "icon_image_url")
    private String iconImageUrl;

    @Column(name = "icon_image_cdn_url")
    private String iconImageCdnUrl;

}
