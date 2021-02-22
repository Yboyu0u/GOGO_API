package com.gogo.GoGo.domain;

import com.gogo.GoGo.controller.dto.user.ImgDto;
import com.gogo.GoGo.controller.dto.user.ModUserDto;
import com.gogo.GoGo.controller.dto.user.UserDto;
import com.gogo.GoGo.domain.dto.Birthday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted = false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String nickname;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String gender;

    @Valid
    @Embedded
    private Birthday birthday;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    private String introduce;

    private String profileImg;

    @ColumnDefault("0") // 이 값이 true가 되면 삭제가 되었다 간주하고 repository에서 삭제됨
    private boolean deleted;

    //TODO: 여행 취향

    public void set(UserDto userDto){
        if(userDto.getUserId() != null){
            this.setUserId(userDto.getUserId());
        }
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

    public void modSet(ModUserDto userDto){
        if(userDto.getNickname() != null){
            this.setNickname(userDto.getNickname());
        }
        if(userDto.getIntroduce() != null){
            this.setIntroduce(userDto.getIntroduce());
        }
        //TODO: 여행 취향

    }
    public void imgSet(ImgDto imgDto){
        if(imgDto.getImg() != null){
            this.setProfileImg(imgDto.getImg().getOriginalFilename());
        }
    }

}
