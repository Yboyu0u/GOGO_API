package com.gogo.GoGo.controller.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
// 순수하게 data로만 쓰이는 class
public class SessionResponseDto {

    private String jwt;
}
