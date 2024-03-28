package com.was.basic.controller;

import com.was.basic.dto.ProductDTO;
import com.was.basic.dto.ProductGroupDTO;
import com.was.basic.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BasicController {

    private final BasicService basicService;
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/app-name")
    public ResponseEntity<Map<String, String>> getAppName() {
        Map<String, String> data = new HashMap<>();
        data.put("appName", appName);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/product-groups")
    public ResponseEntity<List<ProductGroupDTO>> getProductGroups() {
        return ResponseEntity.ok(basicService.getProductGroups());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProductsByProductGroupId(@RequestParam Long productGroupId) {
        List<ProductDTO> products = basicService.getProductsByProductGroupId(productGroupId);
        return ResponseEntity.ok(products);
    }

}
