package com.java.userproject.service;

import com.java.userproject.errors.UserNotFoundException;
import com.java.userproject.model.User;
import com.java.userproject.model.UserRequest;
import com.java.userproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

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

    public User updateUser(Long id, UserRequest userRequest) throws UserNotFoundException {
        User user = this.getUser(id);
        User updatedUser = user.toBuilder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .updatedAt(OffsetDateTime.now())
                .build();
        return userRepository.save(updatedUser);
    }

    public Page<User> getPagedUsers(Integer page, Integer size) {
        Sort.Direction sort =  Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, sort, "id");
        return userRepository.findAll(pageable);
    }

    public List<User> getUsersByAgeRange(Integer lower, Integer upper) {
        return userRepository.findAllByAgeBetween(lower, upper);
    }
}
