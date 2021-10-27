package com.github.coffee.product.controller;

import com.github.coffee.product.dto.ProductDto;
import com.github.coffee.product.entity.Product;
import com.github.coffee.product.service.ProductService;
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
@RequestMapping(value = "/products")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> findAll() {
        LOGGER.info("Fetching list of products");
        List<Product> products = productService.findAll();
        LOGGER.info("Found {} products", products.size());
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") @NotBlank Long id) {
        LOGGER.info("Fetching product, productId {}", id);
        Product product = productService.findById(id);
        LOGGER.info("Fetching product {} success", id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody ProductDto productDto) {
        LOGGER.info("Creating product...");
        productService.create(productDto);
        LOGGER.info("Product created successfully");
        return ResponseEntity.status(HttpStatus.OK).body("Product created successfully! ");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> save(@PathVariable(name = "id") @NotBlank Long id, @Valid @RequestBody ProductDto productDto) {
        LOGGER.info("Updating product, productId {}", id);
        productService.update(id, productDto);
        LOGGER.info("Product {} updated successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully! ");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @NotBlank Long id) {
        LOGGER.info("Deleting product, productId {}", id);
        productService.deleteById(id);
        LOGGER.info("Delete product {} successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully! ");
    }
}
