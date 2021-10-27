package com.github.coffee.product.service;

import com.github.coffee.category.entity.Category;
import com.github.coffee.category.repository.CategoryRepository;
import com.github.coffee.common.exception.NotFoundException;
import com.github.coffee.product.dto.ProductDto;
import com.github.coffee.product.entity.Product;
import com.github.coffee.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Product %s not found", id)));
    }

    public void create(ProductDto createProductDto) {
        Product product = new Product();
        product.mapFromDto(createProductDto);
        setProductCategory(product, createProductDto.getCategoryId());
        productRepository.save(product);
    }

    public void update(Long id, ProductDto updateProductDto) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().mapFromDto(updateProductDto);
            if (!Objects.equals(updateProductDto.getCategoryId(), product.get().getCategory().getId())) {
                setProductCategory(product.get(), updateProductDto.getCategoryId());
            }
            productRepository.save(product.get());
        } else {
            throw new NotFoundException(String.format("Product %s not found", id));
        }
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private void setProductCategory(Product product, Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new NotFoundException(String.format("Category %s not found", categoryId)));
        product.setCategory(category);
    }
}
