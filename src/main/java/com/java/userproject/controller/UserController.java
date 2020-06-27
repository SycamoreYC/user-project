package com.java.userproject.controller;

import com.java.userproject.model.User;
import com.java.userproject.model.UserRequest;
import com.java.userproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Qualifier(value = "userCounter")

    @Value("${spring.datasource.username}")
    private  String username;

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

    @GetMapping("/age-range")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByAgeRange(@RequestParam(name = "lower", defaultValue = "0") Integer lower,
                               @RequestParam(name = "upper", defaultValue = "150") Integer upper) {
        return userService.getUsersByAgeRange(lower, upper);
    }

    @GetMapping("/name-pattern")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByName(@RequestParam(name = "name") String name) {
        return userService.getUsersByName(name);
    }

    @GetMapping("/userName")
    @ResponseStatus(HttpStatus.OK)
    public String getConfigUserName() {
        return "userName: " + username;
    }
}
