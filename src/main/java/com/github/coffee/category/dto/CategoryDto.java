package com.github.coffee.category.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryDto {

    @NotBlank(message = "name is mandatory")
    private String name;

    private String description;

}
