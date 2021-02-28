package com.gogo.GoGo.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckNicknameDto {

    @NotBlank(message = "닉네임을 입력해 주세요")
    private String nickname;
}
