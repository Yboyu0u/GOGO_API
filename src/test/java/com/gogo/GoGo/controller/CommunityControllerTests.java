package com.gogo.GoGo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.GoGo.controller.community.CommunityController;
import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.repository.community.CommunityRepository;
import com.gogo.GoGo.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

//    @Test
//    public void createWithValidAttributes() throws Exception {
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjIsIm5hbWUiOiJzb3BoaWEiLCJuaWNrbmFtZSI6Inlib3kyIn0.Rt3wa5EK7Do3rIScFNx934B3bNuIbb0eU-pDxT82qFs";
//
//
//        CommunityDto dto = CommunityDto.builder()
//                .title("훗카이도로 가자")
//                .content("훗카이도 2박3일 한분 구합니다")
//                .startDate(LocalDate.now())
//                .endDate(LocalDate.now())
//                .build();
////
////        given(communityService.create(dto))
////                .willReturn(Community.builder()
////                        .title(dto.getTitle())
////                        .content(dto.getContent())
////                        .startDate(LocalDate.now())
////                        .endDate(LocalDate.now())
////                        .createdTime(LocalDate.now())
////                        .build());
//
//        mockMvc.perform(post("/api/community/1")
//                .header("Authorization","Bearer"+token)
//                .content(MediaType.APPLICATION_JSON_VALUE)
//                .content(toJsonString(dto)))
//                .andExpect(status().isCreated());
//
////        verify(communityService).create(dto);
//
//    }

    private String toJsonString(CommunityDto dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

}