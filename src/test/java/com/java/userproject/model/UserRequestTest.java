package com.java.userproject.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserRequestTest {

    @Test
    void should_returnCorrectUserInstance() {
        User user = new UserRequest("testName", 18).toUser();

        assertThat(user.getName()).isEqualTo("testName");
        assertThat(user.getAge()).isEqualTo(18);
    }
}
