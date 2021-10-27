package com.github.coffee.category.controller;

import com.github.coffee.category.dto.CategoryDto;
import com.github.coffee.category.entity.Category;
import com.github.coffee.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> findAll() {
        LOGGER.info("Fetching list of categories");
        List<Category> categories = categoryService.findAll();
        LOGGER.info("Found {} categories", categories.size());
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable(name = "id") @NotBlank Long id) {
        LOGGER.info("Fetching category, categoryId {}", id);
        Category category = categoryService.findById(id);
        LOGGER.info("Fetching category {} success", id);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody CategoryDto categoryDto) {
        LOGGER.info("Creating category...");
        categoryService.create(categoryDto);
        LOGGER.info("Category created successfully");
        return ResponseEntity.status(HttpStatus.OK).body("Category created successfully! ");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id") @NotBlank Long id, @Valid @RequestBody CategoryDto categoryDto) {
        LOGGER.info("Updating category, categoryId {}", id);
        categoryService.update(id, categoryDto);
        LOGGER.info("Category {} updated successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body("Category updated successfully! ");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @NotBlank Long id) {
        LOGGER.info("Deleting category, categoryId {}", id);
        categoryService.deleteById(id);
        LOGGER.info("Delete category {} successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully! ");
    }
}
