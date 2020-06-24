package com.java.userproject.service;

import com.java.userproject.errors.UserNotFoundException;
import com.java.userproject.model.User;
import com.java.userproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void should_returnUser_when_getUserSuccessfully() {
        User mockUser = User.builder()
                .id(1L)
                .age(18)
                .name("test")
                .build();

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockUser));

        User actualUser = userService.getUser(1L);
        assertThat(actualUser).isEqualTo(mockUser);
    }

    @Test
    void should_throwException_when_getUserFailed() {
        when(userRepository.findById(1L)).thenThrow(UserNotFoundException.class);

        assertThatThrownBy(() -> userService.getUser(1L)).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void should_returnTrue_when_deleteUserSuccessfully() {
        Boolean result = userService.deleteUser(1L);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void should_returnFalse_when_deleteUserFailed() {
        doThrow(IllegalArgumentException.class).when(userRepository).deleteById(1L);

        Boolean result = userService.deleteUser(1L);

        assertThat(result).isEqualTo(false);
    }
}