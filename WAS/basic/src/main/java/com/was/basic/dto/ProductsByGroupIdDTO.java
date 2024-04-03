package com.was.basic.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductsByGroupIdDTO {

    private String appName;
    private List<ProductDTO> data;

}
