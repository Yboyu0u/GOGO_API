package com.gogo.GoGo.service;

import com.gogo.GoGo.controller.dto.user.ModUserDto;
import com.gogo.GoGo.controller.dto.user.UserDto;
import com.gogo.GoGo.domain.user.Following;
import com.gogo.GoGo.domain.user.User;
import com.gogo.GoGo.domain.utils.Uploader;
import com.gogo.GoGo.exception.*;
import com.gogo.GoGo.repository.user.FollowingRepository;
import com.gogo.GoGo.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private Uploader uploader;

    @Autowired
    private FollowingRepository followingRepository;

    public UserService() {
    }

    //회원 조회
    public User getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("계정이 존재하지 않습니다"));
        return user;
    }

    //회원가입
    public User createUser(UserDto userDto){
        Optional<User> existedUser = userRepository.findByUserId(userDto.getUserId());
        //이미 아이디가 있다면 error 처리
        if(existedUser.isPresent()){
            throw new AlreadyExistedUserIdException();
        }
        Optional<User> existedUsernick = userRepository.findByNickname(userDto.getNickname());
        if(existedUsernick.isPresent()){
            throw new AlreadyExistedNicknameException();
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        user.set(userDto);
        user.setPassword(encodedPassword);
        user.setProfileImg("https://gogoeverybodyy.s3.ap-northeast-2.amazonaws.com/static/gogo.profile.png");
        return userRepository.save(user);
    }

    //아이디 중복 확인
    public void checkUserId(String userId){
        Optional<User> user = userRepository.findByUserId(userId);

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
    public void modifyPerson(Long id, ModUserDto userDto) {
        User user1 = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        User user2 = userRepository.findByNickname(userDto.getNickname())
                .orElseThrow(AlreadyExistedNicknameException::new);

        user1.modSet(userDto);

        userRepository.save(user1);
    }

    // 프로필 사진 업로드
    public void uploadImg(Long id, MultipartFile file) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(NotExistedUserIdException::new);

        user.setProfileImg(uploader.upload(file,"static"));

    }

    //userId 찾기
    public String findUserId(String name, String phoneNumber){
        User user = userRepository.findByNameAndPhoneNumber(name,phoneNumber)
                .orElseThrow(InCorrectInformationException::new);

        return user.getUserId();
    }

    //password 찾기
    public String findPassword(String name,String userId) {
        User user = userRepository.findByNameAndUserId(name,userId)
                .orElseThrow(InCorrectInformationException::new);

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

    //구독하기
    public void addFollowing(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);
        User beFollowedUser = userRepository.findById(id)
                .orElseThrow(NotExistedUserIdException::new);

        if(user.getName() == beFollowedUser.getName()){
            throw new RuntimeException();
        }

        Following following = Following.builder().name(beFollowedUser.getName()).user(user).build();
        followingRepository.save(following);
        user.getFollowingList().add(following);

        userRepository.save(user);

    }

    //구독취소
    public void deleteFollowing(Long id, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);
        User beFollowedUser = userRepository.findById(id)
                .orElseThrow(NotExistedUserIdException::new);

        Following following = followingRepository.findByName(beFollowedUser.getName())
                .orElseThrow(RuntimeException::new);

        followingRepository.delete(following);
    }

    //회원탈퇴
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(NotExistedUserIdException::new);

        user.setDeleted(true);

        userRepository.save(user);
    }

    //password 찾기
    public void changePw(Long id, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(InCorrectInformationException::new);

        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
    }

}
