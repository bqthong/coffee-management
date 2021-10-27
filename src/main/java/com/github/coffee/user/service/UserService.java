package com.github.coffee.user.service;

import com.github.coffee.common.exception.NotFoundException;
import com.github.coffee.user.dto.UserDto;
import com.github.coffee.user.entity.User;
import com.github.coffee.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("User %s not found", id)));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(String.format("User %s not found", username)));
    }

    public void save(Long id, UserDto updateUserDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().mapFromDto(updateUserDto);
            userRepository.save(user.get());
        } else {
            throw new NotFoundException(String.format("User %s not found", id));
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
