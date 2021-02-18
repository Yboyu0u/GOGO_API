package com.gogo.GoGo.controller.dto;

import com.gogo.GoGo.domain.dto.Birthday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    @NotBlank(message = "성별을 선택해 주세요")
    private String gender;

    //@NotBlank(message = "생년월일은 필수값 입니다")
    private LocalDate birthday;

    @NotBlank(message = "전화번호는 필수값 입니다")
    private String phoneNumber;
}
