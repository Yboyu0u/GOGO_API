package com.gogo.GoGo.controller.dto.user;

import com.gogo.GoGo.enumclass.GenderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @NotBlank(message = "Id는 필수값 입니다")
    private String userId;

    @NotBlank(message = "비밀번호는 필수값 입니다")
    private String password;

    @NotBlank(message = "닉네임은 필수값 입니다")
    private String nickname;

    @NotBlank(message = "이름은 필수값 입니다")
    private String name;

    @NotNull(message = "성별을 선택해 주세요")
    private GenderStatus gender;

    //@NotBlank(message = "생년월일은 필수값 입니다")
    private LocalDate birthday;

    @NotBlank(message = "전화번호는 필수값 입니다")
    private String phoneNumber;
}
