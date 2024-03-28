package com.was.basic.service;

import com.was.basic.dto.ProductDTO;
import com.was.basic.dto.ProductGroupDTO;
import com.was.basic.entity.Product;
import com.was.basic.entity.ProductGroup;
import com.was.basic.repository.ProductGroupRepository;
import com.was.basic.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicService {

    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;

    public List<ProductGroupDTO> getProductGroups() {
        return productGroupRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductGroupDTO convertToDTO(ProductGroup productGroup) {
        return ProductGroupDTO.builder()
                .id(productGroup.getId())
                .name(productGroup.getName())
                .build();
    }

    public List<ProductDTO> getProductsByProductGroupId(Long productGroupId) {
        List<Product> products = productRepository.findByProductGroupId(productGroupId);
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .manualLink(product.getManualLink())
                .build();
    }

}
