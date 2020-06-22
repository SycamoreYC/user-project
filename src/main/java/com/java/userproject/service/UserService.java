package com.java.userproject.service;

import com.java.userproject.errors.UserNotFoundException;
import com.java.userproject.model.User;
import com.java.userproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            throw new UserNotFoundException(format("The user with id %s is not exist", id));
        }
    }

    public Boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long saveUser(User user) {
        return userRepository.save(user).getId();
    }
}
