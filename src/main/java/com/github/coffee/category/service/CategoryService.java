package com.github.coffee.category.service;

import com.github.coffee.category.dto.CategoryDto;
import com.github.coffee.category.entity.Category;
import com.github.coffee.category.repository.CategoryRepository;
import com.github.coffee.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Category %s not found", id)));
    }

    public void create(CategoryDto createCategoryDto) {
        Category category = new Category();
        category.mapFromDto(createCategoryDto);
        categoryRepository.save(category);
    }

    public void update(Long id, CategoryDto updateCategoryDto) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.get().mapFromDto(updateCategoryDto);
            categoryRepository.save(category.get());
        } else {
            throw new NotFoundException(String.format("Category %s not found", id));
        }
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
