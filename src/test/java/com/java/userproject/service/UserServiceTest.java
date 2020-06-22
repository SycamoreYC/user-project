package com.java.userproject.service;

import com.java.userproject.model.User;
import com.java.userproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Column;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void should_returnUser_when_getUserSuccessfully() {
        User mockUser = User.builder()
                .id(1L)
                .age(18)
                .name("test")
                .build();
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockUser));

        UserService userService = new UserService(userRepository);
        User actualUser = userService.getUser(1L);
        assertThat(actualUser).isEqualTo(mockUser);
    }
}