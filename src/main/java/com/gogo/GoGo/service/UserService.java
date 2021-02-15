package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.ModUserDto;
import com.gogo.GoGo.controller.dto.UserDto;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.exception.*;
import com.gogo.GoGo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //회원 조회
    public User getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("계정이 존재하지 않습니다"));
        return user;
    }

    //회원가입
    public User createUser(UserDto userDto){
        Optional<User> existedUser = userRepository.findByEmail(userDto.getEmail());
        //이미 이메일이 있다면 error 처리
        if(existedUser.isPresent()){
            throw new AlreadyExistedEmailException();
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        user.set(userDto);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    //이메일 중복 확인
    public void checkEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()){
            throw new AlreadyExistedEmailException();
        }

    }

    // 닉네임 중복 확인
    public void checkNickname(String nickname) {
        Optional<User> user = userRepository.findByNickname(nickname);

        if(user.isPresent()){
            throw new AlreadyExistedNicknameException();
        }

    }

    //프로필 수정
    public User modifyPerson(Long id, ModUserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        user.modSet(userDto);

        return userRepository.save(user);
    }

    //email 찾기
    public String findEmail(String name, String phoneNumber){
        User user = userRepository.findByNameAndPhoneNumber(name,phoneNumber)
                .orElseThrow(InCorrectInformationException::new);

        return user.getEmail();
    }

    //password 찾기
    public String findPassword(String email,String name) {
        User user = userRepository.findByEmailAndName(email,name)
                .orElseThrow(NotExistedEmailException::new);

        String tempPw = UUID.randomUUID().toString().replace("-","");
        tempPw = tempPw.substring(0,10);
        String encodedTempPw = passwordEncoder.encode(tempPw);
        user.setPassword(encodedTempPw);

        return tempPw;
    }

    //로그인
    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(NotExistedEmailException::new);

        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new PasswordWrongException();
        }

        return user;
    }

    //회원탈퇴
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        user.setDeleted(true);

        userRepository.save(user);
    }
}
