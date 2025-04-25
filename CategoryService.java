package com.nimap.service;

import com.nimap.dto.CategoryDTO;
import com.nimap.dto.ProductDTO;
import com.nimap.entity.Category;
import com.nimap.entity.Product;
import com.nimap.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

	 @Autowired
	    private CategoryRepository categoryRepository;
	 

	    public Page<CategoryDTO> getAllCategories(int page, int size) {
	        Page<Category> categories = categoryRepository.findAll(PageRequest.of(page, size));
	        return categories.map(this::convertToDTO);
	    }

	    public CategoryDTO getCategoryById(Long id) {
	        Category category = categoryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found"));
	        return convertToDTO(category);
	    }

	    public CategoryDTO createCategory(Category category) {
	        Category saved = categoryRepository.save(category);
	        return convertToDTO(saved);
	    }

	    public CategoryDTO updateCategory(Long id, Category updatedCategory) {
	        Category existing = categoryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found"));
	        existing.setName(updatedCategory.getName());
	        return convertToDTO(categoryRepository.save(existing));
	    }

	    public void deleteCategory(Long id) {
	        categoryRepository.deleteById(id);
	    }

	    private CategoryDTO convertToDTO(Category category) {
	        CategoryDTO dto = new CategoryDTO();
	        dto.setId(category.getId());
	        dto.setName(category.getName());

	        List<ProductDTO> productDTOs = new ArrayList<>();
	        if (category.getProducts() != null) {
	            for (Product product : category.getProducts()) {
	                ProductDTO pdto = new ProductDTO();
	                pdto.setId(product.getId());
	                pdto.setName(product.getName());
	                productDTOs.add(pdto);
	            }
	        }

	        dto.setProducts(productDTOs);
	        return dto;
	    }
}
