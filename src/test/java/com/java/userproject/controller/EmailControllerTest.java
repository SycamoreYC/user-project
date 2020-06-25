package com.java.userproject.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmailController.class)
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_returnUserEmailCorrectly() throws Exception {
        MvcResult result = mockMvc.perform(get("/emails/1"))
                .andExpect(status().isOk()).andReturn();

        String resultBody = result.getResponse().getContentAsString();

        assertThat(resultBody).isEqualTo("1@rest.local");
    }
}