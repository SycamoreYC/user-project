package com.java.userproject.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class UserRequest {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Age cannot be null")
    private Integer age;

    public User toUser() {
        return User.builder().name(name).age(age).build();
    }
}
