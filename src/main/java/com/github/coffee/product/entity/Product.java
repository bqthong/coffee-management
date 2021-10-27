package com.github.coffee.product.entity;

import com.github.coffee.category.entity.Category;
import com.github.coffee.product.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;


    public void mapFromDto(ProductDto productDto) {
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.imageUrl = productDto.getImageUrl();
        this.active = productDto.getActive();
    }
}
