package com.nimap.service;

import com.nimap.dto.CategoryDTO;
import com.nimap.dto.ProductDTO;
import com.nimap.entity.Category;
import com.nimap.entity.Product;

import com.nimap.repository.CategoryRepository;
import com.nimap.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDTO> getAllProducts(int page, int size) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
        return products.map(this::convertToDTO);
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDTO(product);
    }

    public ProductDTO createProduct(Product product) {
        Product saved = productRepository.save(product);
        return convertToDTO(saved);
    }

    public ProductDTO updateProduct(Long id, Product updatedProduct) {
        Product existing = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        return convertToDTO(productRepository.save(existing));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());

        Category category = product.getCategory();
        CategoryDTO catDto = new CategoryDTO();
        catDto.setId(category.getId());
        catDto.setName(category.getName());

        dto.setCategory(catDto);
        return dto;
    }
}
