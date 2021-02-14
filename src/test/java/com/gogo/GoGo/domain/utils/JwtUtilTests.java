package com.gogo.GoGo.domain.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void getClaims(){
        String token = " eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJtYXJ0aW4ifQ.IRArZczGdJI2K5DIf5Qlvg-YhTS-FFmnj8QFGkqHJ5A";

        Claims claims = jwtUtil.getClaims(token);

        assertThat(claims.get("userId",Long.class)).isEqualTo(1L);
        assertThat(claims.get("name")).isEqualTo("martin");


    }

}