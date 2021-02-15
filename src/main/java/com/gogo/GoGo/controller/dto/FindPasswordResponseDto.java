package com.gogo.GoGo.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindPasswordResponseDto {

    private String password;
}
