package com.gogo.GoGo.domain;

import com.gogo.GoGo.controller.dto.ImgDto;
import com.gogo.GoGo.controller.dto.ModUserDto;
import com.gogo.GoGo.controller.dto.UserDto;
import com.gogo.GoGo.domain.dto.Birthday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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
    private String userId;
    //@NotEmpty
    private String email;
    //@NotEmpty
    private String password;m
    //@NotEmpty
    private String nickname;

   // @NotEmpty
    private String name;

  //  @NotEmpty
    private String gender;

    @Embedded
    private Birthday birthday;

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

        if(userDto.getEmail() != null){
            this.setEmail(userDto.getEmail());
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

    }
    public void imgSet(ImgDto imgDto){
        if(imgDto.getImg() != null){
            this.setProfileImg(imgDto.getImg().getOriginalFilename());
        }
    }

}
