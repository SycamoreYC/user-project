package com.java.userproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.userproject.model.User;
import com.java.userproject.model.UserRequest;
import com.java.userproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void should_returnUserInfo_when_getUserSuccessfully() throws Exception {
        User mockUser = User.builder()
                .id(1L)
                .age(18)
                .name("test")
                .build();
        given(userService.getUser(1L)).willReturn(mockUser);
        MvcResult response = mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk()).andReturn();

        String json = response.getResponse().getContentAsString();
        User result = new ObjectMapper().readValue(json, User.class);

        assertThat(result).isEqualTo(mockUser);
    }

    @Test
    void should_returnUserId_when_saveUserSuccessfully() throws Exception {
        UserRequest userRequest = new UserRequest("testName", 18);
        given(userService.saveUser(any(User.class))).willReturn(1L);
        MvcResult response = mockMvc.perform(post("/users")
                .contentType("application/json").content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isCreated()).andReturn();

        String result = response.getResponse().getContentAsString();

        assertThat(result).isEqualTo("1");
    }

    @Test
    void should_returnTrue_when_deleteUserSuccessfully() throws Exception {
        given(userService.deleteUser(any(Long.class))).willReturn(true);
        MvcResult response = mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk()).andReturn();

        String result = response.getResponse().getContentAsString();

        assertThat(result).isEqualTo("true");
    }

    @Test
    void should_returnUser_when_updateUserSuccessfully() throws Exception {
        UserRequest userRequest = new UserRequest("testName", 18);
        User updatedUser = userRequest.toUser().toBuilder().id(1L).build();
        given(userService.updateUser(eq(1L), any(UserRequest.class))).willReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                .contentType("application/json").content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("testName")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void should_returnPage_when_pagingQuerySuccessfully() throws Exception {
        User mockUser = User.builder()
                .id(1L)
                .age(18)
                .name("test")
                .build();

        given(userService.getPagedUsers(any(Integer.class), any(Integer.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(mockUser)));
        mockMvc.perform(get("/users/page/0/size/1"))
                .andExpect(jsonPath("$.content[0].name", is("test")))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.last", is(true)))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void should_returnUserList_when_quryByAgeRange() throws Exception {
        given(userService.getUsersByAgeRange(any(Integer.class), any(Integer.class)))
                .willReturn(Collections.emptyList());
        MvcResult response = mockMvc.perform(get("/users/age-range?lower=1&upper=10"))
                .andExpect(status().isOk()).andReturn();

        String result = response.getResponse().getContentAsString();

        assertThat(result).isEqualTo("[]");
    }

    @Test
    void should_returnUserList_when_queryByNamePattern() throws Exception {
        given(userService.getUsersByName(any(String.class)))
                .willReturn(Collections.emptyList());

        MvcResult response = mockMvc.perform(get("/users/name-pattern?name=trump"))
                .andExpect(status().isOk()).andReturn();

        String result = response.getResponse().getContentAsString();

        assertThat(result).isEqualTo("[]");
    }
}
