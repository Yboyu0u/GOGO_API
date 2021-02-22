package com.gogo.GoGo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.GoGo.controller.dto.user.SessionRequestDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.domain.utils.JwtUtil;
import com.gogo.GoGo.exception.NotExistedUserIdException;
import com.gogo.GoGo.exception.PasswordWrongException;
import com.gogo.GoGo.repository.UserRepository;
import com.gogo.GoGo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@Transactional
public class SessionControllerTests {

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
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
    //로그인이 성공했을 때
    void loginWithValidAttributes() throws Exception {
        SessionRequestDto dto = SessionRequestDto.builder()
                .userId("example123")
                .password("1234")
                .build();

        User mockUser = User.builder()
                .id(100L)
                .userId(dto.getUserId())
                .name("martin")
                .password(dto.getPassword())
                .build();

        given(userService.authenticate(dto.getUserId(),dto.getPassword()))
                .willReturn(mockUser);

        given(jwtUtil.createToken(100L,"martin"))
                .willReturn("header.payload.signature");


        mockMvc.perform(post("/api/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("{\"jwt\":\"header.payload.signature\"}")));

        verify(userService).authenticate(eq("example123"),eq("1234"));
    }

    @Test
    // PasswordWrong일때
    void loginWithWrongPassword() throws Exception {
        given(userService.authenticate("fbduddn97","X"))
                .willThrow(PasswordWrongException.class);


        mockMvc.perform(post("/api/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"fbduddn97\",\"password\":\"X\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("fbduddn97"),eq("X"));
    }

    @Test
    //NotExistedUserId일때
    void loginWithNotExistedUserId() throws Exception {
        given(userService.authenticate("X","1234"))
                .willThrow(NotExistedUserIdException.class);

        mockMvc.perform(post("/api/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":\"X\",\"password\":\"1234\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("X"),eq("1234"));
    }

    private String toJsonString(SessionRequestDto dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

}