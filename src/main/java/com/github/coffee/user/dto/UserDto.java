package com.github.coffee.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "first name is mandatory")
    private String firstName;

    @NotBlank(message = "last name is mandatory")
    private String lastName;

    @NotBlank(message = "email is mandatory")
    @Email(message = "invalid email")
    private String email;

    private String phone;

    @NotNull(message = "active status is not null")
    private Boolean active;

    @NotBlank(message = "role is mandatory")
    private String role;

}
