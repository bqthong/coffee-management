package com.github.coffee.product.dto;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotNull(message = "price is mandatory")
    private BigDecimal price;

    private String imageUrl;

    @NotNull(message = "active status is not null")
    private Boolean active;

    @NotNull(message = "categoryId is not null")
    private Long categoryId;

}
