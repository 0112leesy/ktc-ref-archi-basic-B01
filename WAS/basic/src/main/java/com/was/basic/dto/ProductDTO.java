package com.was.basic.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private String manualLink;
    private String iconImageUrl;

}
