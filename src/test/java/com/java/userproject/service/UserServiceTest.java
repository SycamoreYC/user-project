package com.java.userproject.service;

import com.java.userproject.errors.UserNotFoundException;
import com.java.userproject.model.User;
import com.java.userproject.model.UserRequest;
import com.java.userproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
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

    @Test
    void should_returnUserId_when_saveUserSuccessfully() {
        User mockUser = User.builder()
                .age(18)
                .name("test")
                .build();

        when(userRepository.save(mockUser))
                .thenReturn(mockUser.toBuilder().id(1L).createdAt(OffsetDateTime.now()).build());

        Long id = userService.saveUser(mockUser);

        assertThat(id).isEqualTo(1L);
    }

    @Test
    void should_returnUser_when_updateUserSuccessfully() {
        User mockUser = User.builder()
                .id(1L)
                .age(18)
                .name("test")
                .build();
        User modifiedUser = User
                .builder()
                .id(1L)
                .age(20)
                .name("update")
                .build();
        UserRequest mockUserRequest = new UserRequest("update", 20);

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(modifiedUser)).thenReturn(modifiedUser);

        assertThat(userService.updateUser(1L, mockUserRequest)).isEqualTo(modifiedUser);
    }

    @Test
    void should_returnPage_when_pagingQuerySuccessfully() {
        User mockUser = User.builder()
                .id(1L)
                .age(18)
                .name("test")
                .build();
        int page = 0;
        int size = 1;
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");

        doReturn(new PageImpl<>(Collections.singletonList(mockUser))).when(userRepository).findAll(pageable);

        assertThat(userService.getPagedUsers(page, size)).isInstanceOf(Page.class);
    }

    @Test
    void should_returnUserList_when_queryByAgeRange() {
        int lower = 10;
        int upper = 11;

        doReturn(Collections.emptyList()).when(userRepository).findAllByAgeBetween(lower, upper);

        List<User> result = userService.getUsersByAgeRange(lower, upper);

        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void should_returnUserList_when_queryByNamePattern() {
        String name = "test";

        doReturn(Collections.emptyList()).when(userRepository).findAllByNameContains(name);

        List<User> result = userService.getUsersByName(name);

        assertThat(result.size()).isEqualTo(0);
    }
}
