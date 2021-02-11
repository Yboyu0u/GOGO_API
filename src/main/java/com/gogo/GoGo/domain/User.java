package com.gogo.GoGo.domain;

import com.gogo.GoGo.controller.dto.UserDto;
import com.gogo.GoGo.domain.dto.Birthday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String name;

    @NotEmpty
    private String gender;

    @Embedded
    private Birthday birthday;

    private String phoneNumber;

    public void set(UserDto userDto){
        if(userDto.getEmail() != null){
            this.setEmail(userDto.getEmail());
        }
//        if(userDto.getPassword() != null){
//            this.setPassword(userDto.getPassword());
//        }
        if(userDto.getNickname() != null){
            this.setNickname(userDto.getNickname());
        }
        if(userDto.getName() != null){
            this.setName(userDto.getName());
        }
        if(userDto.getGender() != null){
            this.setGender(userDto.getGender());
        }
        if(userDto.getBirthday() != null){
            this.setBirthday(Birthday.of(userDto.getBirthday()));
        }
        if(userDto.getPhoneNumber() != null){
            this.setPhoneNumber(userDto.getPhoneNumber());
        }
    }



}
