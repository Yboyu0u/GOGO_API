package com.gogo.GoGo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserIDRequestDto {

    private String name;
    private String phoneNumber;
}
