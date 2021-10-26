package com.github.coffee.user.controller;

import com.github.coffee.user.dto.UserDto;
import com.github.coffee.user.entity.User;
import com.github.coffee.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable(name = "id") @NotBlank Long id) {
        User user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> save(@PathVariable(name = "id") @NotBlank Long id, @Valid @RequestBody UserDto userDto) {
        userService.save(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully! ");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @NotBlank Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully! ");
    }
}
