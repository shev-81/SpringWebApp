package com.exemple.spring.integrations;

import com.exemple.spring.core.ProductDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Data
@Component
public class ProductServiceIntegration {

    private RestTemplate restTemplate;

    @Value("${integrations.core-service.url}")
    private String productServiceUrl;

    public Optional <ProductDto> findById(Long id){
        ProductDto productDto = restTemplate.getForObject(productServiceUrl + "/api/v1/products/" + id, ProductDto.class);
        return Optional.of(productDto);
    }
}
