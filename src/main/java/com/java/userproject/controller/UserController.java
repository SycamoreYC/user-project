package com.java.userproject.controller;

import com.java.userproject.model.User;
import com.java.userproject.model.UserRequest;
import com.java.userproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.saveUser(userRequest.toUser());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @GetMapping("/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<User> getPagedUsers(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return userService.getPagedUsers(page, size);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByAgeRange(@RequestParam(name = "lower", defaultValue = "0") Integer lower,
                               @RequestParam(name = "upper", defaultValue = "150") Integer upper) {
        return userService.getUsersByAgeRange(lower, upper);
    }
}
