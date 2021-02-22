package com.gogo.GoGo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.controller.dto.user.UserDto;
import com.gogo.GoGo.domain.Community;
import com.gogo.GoGo.repository.CommunityRepository;
import com.gogo.GoGo.repository.UserRepository;
import com.gogo.GoGo.service.CommunityService;
import com.gogo.GoGo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@Transactional
public class CommunityControllerTests {
    @Autowired
    private CommunityController communityController;


    private MockMvc mockMvc;

    @MockBean
    private CommunityService communityService;

    @Autowired
    private CommunityRepository communityRepository;

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
    public void create() throws Exception {

        CommunityDto dto = CommunityDto.builder()
                .title("훗카이도로 가자")
                .content("훗카이도 2박3일 한분 구합니다")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();


        mockMvc.perform(post("/api/community")
                .content(MediaType.APPLICATION_JSON_VALUE)
                .content(toJsonString(dto)))
                .andExpect(status().isCreated());

    }

    private String toJsonString(CommunityDto dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

}