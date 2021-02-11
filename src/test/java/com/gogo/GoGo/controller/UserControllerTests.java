package com.gogo.GoGo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.GoGo.controller.dto.UserDto;
import com.gogo.GoGo.repository.UserRepository;
import com.gogo.GoGo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@Transactional
public class UserControllerTests {

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void beforeEach() throws Exception{
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
    }

    @Test
    void create() throws Exception{

        UserDto userDto = UserDto.builder()
                .email("fbduddn97@example.com")
                .password("1234")
                .nickname("gogo")
                .name("martin")
                .gender("male")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .build();

        mockMvc.perform(post("/api/user")
        .content(MediaType.APPLICATION_JSON_VALUE)
        .content(toJsonString(userDto)))
                .andExpect(status().isCreated());




    }

    private String toJsonString(UserDto userDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userDto);
    }


}