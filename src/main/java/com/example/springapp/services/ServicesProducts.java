package com.example.springapp.services;

import com.example.springapp.data.Product;
import com.example.springapp.repositories.ProductRepository;
import com.example.springapp.repositories.specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ServicesProducts {

    private ProductRepository productRepository;

    public ServicesProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> find(Integer minScore, Integer maxScore, String partName, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minScore != null) {
            spec = spec.and(ProductSpecifications.scoreGreaterOrEqualsThan(minScore));
        }
        if (maxScore != null) {
            spec = spec.and(ProductSpecifications.scoreLessThanOrEqualsThan(maxScore));
        }
        if (partName != null) {
            spec = spec.and(ProductSpecifications.nameLike(partName));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }

    public Product getProductById(Long id){
        Product prod = productRepository.findById(id).get();
        return prod;
    }

    public void delProdictById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
}
