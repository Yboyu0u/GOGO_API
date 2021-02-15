package com.gogo.GoGo.controller.dto;

import com.gogo.GoGo.domain.dto.Birthday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {

    private String email;

    private String password;

    private String nickname;

    private String name;

    private String gender;

    private LocalDate birthday;

    private String phoneNumber;

}
