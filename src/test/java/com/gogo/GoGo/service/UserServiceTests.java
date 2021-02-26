package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.user.ModUserDto;
import com.gogo.GoGo.controller.dto.user.UserDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.domain.dto.Birthday;
import com.gogo.GoGo.exception.AlreadyExistedUserIdException;
import com.gogo.GoGo.exception.NotExistedUserIdException;
import com.gogo.GoGo.exception.PasswordWrongException;
import com.gogo.GoGo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
/*
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    void createUser(){
        UserDto userDto = UserDto.builder()
                .userId("fbduddn97")
                .password("1234")
                .nickname("gogo")
                .name("UUU")
                .gender("male")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .build();


        userService.createUser(userDto);

        verify(userRepository).save(argThat(new IsUserWillBeInserted()));
    }

    @Test
    void createUserWithExistedUserId(){

        when(userRepository.findByUserId("fbduddn97"))
                .thenReturn(Optional.of(User.builder().userId("fbduddn97").build()));

        assertThrows(AlreadyExistedUserIdException.class, () -> userService.createUser(mockUserDto()));
    }

    @Test
    public void authenticatedWithValidAttributes(){
        String userId = "fbduddn97";
        String password = "1234";

        User mockUser = User.builder()
                .userId(userId)
                .password(password)
                .build();


        given(userRepository.findByUserId(userId))
                .willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(),any())).willReturn(true);

        User user = userService.authenticate(userId,password);

        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @Test
    public void authenticatedWithNotExistedEmail(){
        String userId = "example1";
        String password = "1234";


        given(userRepository.findByUserId(userId))
                .willReturn(Optional.empty());

        assertThrows(NotExistedUserIdException.class, () -> userService.authenticate(userId,password));
    }

    @Test
    public void authenticateWithWrongPassword(){
        String userId = "fbduddn97";
        String password = "X";


        given(userRepository.findByUserId(userId))
                .willReturn(Optional.of(User.builder().userId("fbduddn97").password("1234").build()));

        given(passwordEncoder.matches(any(),any())).willReturn(false);

        assertThrows(PasswordWrongException.class, () -> userService.authenticate(userId,password));
    }

    @Test
    public void deleteUser(){
        given(userRepository.findById(1L))
                .willReturn(Optional.of(User.builder().name("Yboy").build()));

        userService.deleteUser(1L);

        User deleteUser = userRepository.findById(1L).orElse(null);
    }

    @Test
    public void modifyUser(){
        ModUserDto userDto = ModUserDto.builder()
                .nickname("gogo")
                .introduce("여행러버입니당")
                .build();


        given(userRepository.findById(1L))
                .willReturn(Optional.of(User.builder().name("Yboy").build()));

        userService.modifyPerson(1L,userDto);

        verify(userRepository,times(1)).save(argThat(new IsUserWillBeUpdated()));
    }


    private static class IsUserWillBeInserted implements ArgumentMatcher<User>{

        UserDto userDto = UserDto.builder()
                .userId("fbduddn97")
//                .password("1234")
                .nickname("gogo")
                .name("UUU")
                .gender("male")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .build();
        @Override
        public boolean matches(User user) {
            return equals(user.getUserId(),"fbduddn97")
//                    && equals(user.getPassword(),"1234")
                    && equals(user.getNickname(),"gogo")
                    && equals(user.getName(),"UUU")
                    && equals(user.getGender(),"male")
                    && equals(user.getBirthday(), Birthday.of(LocalDate.now()))
                    && equals(user.getPhoneNumber(),"010-9283-6657");
        }
        private boolean equals(Object actual, Object expected){
            return expected.equals(actual);
        }
    }

    private static class IsUserWillBeUpdated implements ArgumentMatcher<User>{
        ModUserDto userDto = ModUserDto.builder()
                .nickname("gogo")
                .introduce("여행러버입니당")
                .build();
        @Override
        public boolean matches(User user) {
            return equals(user.getNickname(), "gogo")
                    && equals(user.getIntroduce(), "여행러버입니당");
        }
        private boolean equals(Object actual, Object expected){
            return expected.equals(actual);
        }
    }

    private UserDto mockUserDto(){
        return  UserDto.builder()
                .userId("fbduddn97")
                .password("1234")
                .nickname("gogo")
                .name("UUU")
                .gender("male")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .build();
    }

}

 */