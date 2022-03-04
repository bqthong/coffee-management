package com.github.coffee.user.controller;

import com.github.coffee.user.dto.UserDto;
import com.github.coffee.user.entity.User;
import com.github.coffee.user.service.UserService;
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
@RequestMapping("${api.prefix}/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> findAll() {
        LOGGER.info("Fetching list of users");
        List<User> users = userService.findAll();
        LOGGER.info("Found {} users", users.size());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable(name = "id") @NotBlank Long id) {
        LOGGER.info("Fetching user, userId {}", id);
        User user = userService.findById(id);
        LOGGER.info("Fetching user {} success", id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> save(@PathVariable(name = "id") @NotBlank Long id, @Valid @RequestBody UserDto userDto) {
        LOGGER.info("Updating user, userId {}", id);
        userService.save(id, userDto);
        LOGGER.info("User {} updated successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully! ");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") @NotBlank Long id) {
        LOGGER.info("Deleting user, userId {}", id);
        userService.deleteById(id);
        LOGGER.info("Delete user {} successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully! ");
    }
}
