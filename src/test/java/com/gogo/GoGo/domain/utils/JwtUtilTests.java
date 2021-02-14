package com.gogo.GoGo.domain.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTests {

    private static final String SECRET = "12345678901234567890123456789012";
    private JwtUtil jwtUtil;

    @BeforeEach
    public void beforeEach(){
        jwtUtil = new JwtUtil(SECRET);
    }
    @Test
    public void createToken(){

        String token = jwtUtil.createToken(1L,"martin");

        assertThat(token, containsString("."));
    }
}