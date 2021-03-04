package com.gogo.GoGo.service;


import com.gogo.GoGo.controller.dto.user.UserPersonalDTO;
import com.gogo.GoGo.domain.Personal;
import com.gogo.GoGo.domain.User;
import com.gogo.GoGo.repository.PersonalRepository;
import com.gogo.GoGo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@Slf4j
public class PersonalService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    UserService userService;

    public void save(Personal personal) {
        personalRepository.save(personal);
    }

    public Personal getPersonal(Long id) {
        Personal personal = personalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PERSONAL ID 존재 안"));
        return personal;
    }

    public Personal UserSetPersonal(UserPersonalDTO userPersonalDTO) {
        Personal personal = new Personal();
        personal.settingPersonal(userPersonalDTO);
        personalRepository.save(personal);
        return personal;
    }





    public void create(UserPersonalDTO dto, Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        Personal personal = new Personal();
       // personal.setUser(user);
      //  personal.set(dto);
        personalRepository.save(personal);
    }



    public Personal Find(Long id) {
        User now_user = userService.getUser(id);
        int Filter_1 = now_user.getPersonal().getScore_Q1();
        int Filter_2 = now_user.getPersonal().getScore_Q2();
        int Filter_3 = now_user.getPersonal().getScore_Q3();
        int Filter_4 = now_user.getPersonal().getScore_Q4();

        List<Personal> userList = personalRepository.findAll();
        // 모든 성향 목록가져온다.

        List<Personal> Q1 = new ArrayList<>();
        List<Personal> Q2 = new ArrayList<>();
        List<Personal> Q3 = new ArrayList<>();
        List<Personal> Q4 = new ArrayList<>();


        for (Personal personal : userList) {
            //첫버째 필터 적용.
            if(personal.getScore_Q1()<=(Filter_1+1) && personal.getScore_Q1()>=(Filter_1-1)) {
                Q1.add(personal);
                if(personal.getId() == now_user.getPersonal().getId()) Q1.remove(personal);
            }

            //첫버째 필터 적용.
            if(personal.getScore_Q2()<=(Filter_2+1) && personal.getScore_Q2()>=(Filter_2-1)) {
                Q2.add(personal);
                if(personal.getId() == now_user.getPersonal().getId()) Q2.remove(personal);
            }

            if(personal.getScore_Q3()<=(Filter_3+1) && personal.getScore_Q3()>=(Filter_3-1)) {
                Q3.add(personal);
                if(personal.getId() == now_user.getPersonal().getId()) Q3.remove(personal);
            }

            //첫버째 필터 적용.
            if(personal.getScore_Q4()<=(Filter_4+1) && personal.getScore_Q4()>=(Filter_4-1)) {
                Q4.add(personal);
                if(personal.getId() == now_user.getPersonal().getId()) Q4.remove(personal);
            }
        }

        Random random = new Random();


        int listSize1 = Q1.size();
        int listSize2 = Q2.size();
        int listSize3 = Q3.size();
        int listSize4 = Q4.size();

        int randomIndex1 = random.nextInt(listSize1);
        int randomIndex2 = random.nextInt(listSize2);
        int randomIndex3 = random.nextInt(listSize3);
        int randomIndex4 = random.nextInt(listSize4);

        Personal personal_1 = Q1.get(randomIndex1);
        Personal personal_2 = Q2.get(randomIndex2);
        Personal personal_3 = Q3.get(randomIndex3);
        Personal personal_4 = Q4.get(randomIndex4);


        return personal_1;
    }



}


