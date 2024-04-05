package com.was.basic.service;

import com.was.basic.dto.ProductDTO;
import com.was.basic.dto.ProductGroupDTO;
import com.was.basic.dto.ProductsByGroupIdDTO;
import com.was.basic.entity.Product;
import com.was.basic.entity.ProductGroup;
import com.was.basic.repository.ProductGroupRepository;
import com.was.basic.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicService {

    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(BasicService.class);
    private static final String LOG_FILE_PATH = "/home/ubuntu/nas_log/";

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

    public ProductsByGroupIdDTO getProductsByProductGroupId(Long productGroupId, String appName) {
        List<Product> products = productRepository.findByProductGroupId(productGroupId);
        logger.info("[{}] '{}' 상품 목록이 조회되었습니다.", appName, getProductGroupName(productGroupId));
        return ProductsByGroupIdDTO.builder()
                .appName(appName)
                .data(products.stream()
                        .filter(product -> product.getIconImageUrl() != null)
                        .map(this::convertToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .manualLink(product.getManualLink())
                .iconImageUrl(product.getIconImageUrl())
                .build();
    }

    private String getProductGroupName(Long productGroupId) {
        return productGroupRepository.findById(productGroupId).get().getName();
    }

    public List<String> getRecentLogs(String filename) {
        List<String> recentLogs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH + filename))) {
            String line;
            int lineCount = 0;
            // 파일의 마지막 20줄만 읽어옴
            while ((line = reader.readLine()) != null) {
                if (lineCount >= 20) {
                    recentLogs.remove(0); // 가장 오래된 로그 제거
                }
                recentLogs.add(line);
                lineCount++;
            }
        } catch (IOException e) {
            logger.error("Error reading log file: {}", e.getMessage());
        }
        return recentLogs;
    }

}
