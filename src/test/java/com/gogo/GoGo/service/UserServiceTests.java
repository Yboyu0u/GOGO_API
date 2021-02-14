package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.SessionRequestDto;
import com.gogo.GoGo.controller.dto.UserDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.domain.dto.Birthday;
import com.gogo.GoGo.exception.AlreadyExistedEmailException;
import com.gogo.GoGo.exception.NotExistedEmailException;
import com.gogo.GoGo.exception.PasswordWrongException;
import com.gogo.GoGo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
                .email("fbduddn97@example.com")
                .password("1234")
                .nickname("gogo")
                .name("UUU")
                .gender("male")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .build();


        userService.createUser(userDto);

        verify(userRepository).save(argThat(new IsUserWillBeUpdated()));
    }

    @Test
    void createUserWithExistedEmail(){

        when(userRepository.findByEmail("fbduddn97@example.com"))
                .thenReturn(Optional.of(User.builder().email("fbduddn97@example.com").build()));

        assertThrows(AlreadyExistedEmailException.class, () -> userService.createUser(mockUserDto()));
    }

    @Test
    public void authenticatedWithValidAttributes(){
        String email = "fbduddn97@example.com";
        String password = "1234";

        User mockUser = User.builder()
                .email(email)
                .password(password)
                .build();


        given(userRepository.findByEmail(email))
                .willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(),any())).willReturn(true);

        User user = userService.authenticate(email,password);

        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void authenticatedWithNotExistedEmail(){
        String email = "X@example.com";
        String password = "1234";


        given(userRepository.findByEmail(email))
                .willReturn(Optional.empty());

        assertThrows(NotExistedEmailException.class, () -> userService.authenticate(email,password));
    }

    @Test
    public void authenticateWithWrongPassword(){
        String email = "fbduddn97@example.com";
        String password = "X";


        given(userRepository.findByEmail(email))
                .willReturn(Optional.of(User.builder().email("fbduddn97@example.com").password("1234").build()));

        given(passwordEncoder.matches(any(),any())).willReturn(false);

        assertThrows(PasswordWrongException.class, () -> userService.authenticate(email,password));
    }


    private static class IsUserWillBeUpdated implements ArgumentMatcher<User>{

        UserDto userDto = UserDto.builder()
                .email("fbduddn97@example.com")
//                .password("1234")
                .nickname("gogo")
                .name("UUU")
                .gender("male")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .build();
        @Override
        public boolean matches(User user) {
            return equals(user.getEmail(),"fbduddn97@example.com")
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

    private UserDto mockUserDto(){
        return  UserDto.builder()
                .email("fbduddn97@example.com")
                .password("1234")
                .nickname("gogo")
                .name("UUU")
                .gender("male")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .build();
    }

}