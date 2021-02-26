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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public User createUser(UserDto userdto){
        Optional<User> existedUser = userRepository.findByUserId(userdto.getUserId());
        //이미 이메일이 있다면 error 처리
        if(existedUser.isPresent()){
            throw new AlreadyExistedUserIdException();
        }

        String encodedPassword = passwordEncoder.encode(userdto.getPassword());

        User user = new User();
        user.set(userdto);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    //아이디 중복 확인
    public void checkUserId(String userId){
        Optional<User> user = userRepository.findByUserId(userId);
    }

    //이메일 중복 확인
    public void checkEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()){
            throw new AlreadyExistedUserIdException();
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

    // 프로필 사진 업로드
    public void uploadImg(Long id, MultipartFile img) {
        String upload_path = "/Users/youngwooyoo/desktop/GoGo /src/main/resources/static/images/profile/";
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        try {
            if(user.getProfileImg() != null){
                File oImg = new File(upload_path + user.getProfileImg());
                oImg.delete();
            }
            img.transferTo(new File(upload_path+img.getOriginalFilename()));

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        user.setProfileImg(img.getOriginalFilename());

    }

    //userId 찾기
    public String findUserId(String name, String email){
        User user = userRepository.findByNameAndEmail(name,email)
                .orElseThrow(InCorrectInformationException::new);

        return user.getEmail();
    }

    //password 찾기
    public String findPassword(String userId,String name) {
        User user = userRepository.findByNameAndEmail(userId,name)
                .orElseThrow(NotExistedUserIdException::new);

        String tempPw = UUID.randomUUID().toString().replace("-","");
        tempPw = tempPw.substring(0,10);
        String encodedTempPw = passwordEncoder.encode(tempPw);
        user.setPassword(encodedTempPw);

        return tempPw;
    }

    //로그인
    public User authenticate(String userId, String password) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(NotExistedUserIdException::new);

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
