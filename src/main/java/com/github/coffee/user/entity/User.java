package com.github.coffee.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.coffee.user.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "role")
    private String role;

    public void mapFromDto(UserDto userDto) {
        this.username = userDto.getUsername();
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.active = userDto.getActive();
        this.role = userDto.getRole();
    }
}
