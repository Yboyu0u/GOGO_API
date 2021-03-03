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


    public Personal getPersonalByUID(Long id) {

        Personal personal = personalRepository.findByUSERID(id)
                .orElseThrow(() -> new RuntimeException("PERSONAL ID 존재 안"));

        return personal;
    }


    public void create(UserPersonalDTO dto, Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        Personal personal = new Personal();
        personal.setUser(user);
        personal.set(dto);
        personalRepository.save(personal);
    }




    public User FindMatch(Long id) {
        User now =new User();
        now = userService.getUser(id);
        int Q1 = now.getPersonal().getScore_Q1();
        int Q2 = now.getPersonal().getScore_Q2();
        int Q3 = now.getPersonal().getScore_Q3();
        int Q4 = now.getPersonal().getScore_Q4();
        List<User> users= userRepository.findAll();
        for (User user : users) {
            if(user == now) users.remove(user);
            if(!(user.getPersonal().getScore_Q1() >= (Q1-1) && user.getPersonal().getScore_Q1()<= (Q1+1))) {
                users.remove(user);
            }
        }
        User like = new User();
        Random random = new Random();
        like = users.get(random.nextInt(users.size()));
        return like;

    }



}


