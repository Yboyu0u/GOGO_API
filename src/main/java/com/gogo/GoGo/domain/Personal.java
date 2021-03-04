package com.gogo.GoGo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gogo.GoGo.controller.dto.user.UserPersonalDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "P3")
public class Personal {

    @Id
    @GeneratedValue
    @Column(name = "PERSONAL_ID")
    private Long id;

    private int score_Q1;
    private int score_Q2;
    private int score_Q3;
    private int score_Q4;

    public void settingPersonal(UserPersonalDTO userPersonalDTO) {
        this.score_Q1 = userPersonalDTO.getScore_Q1();
        this.score_Q2 = userPersonalDTO.getScore_Q2();
        this.score_Q3 = userPersonalDTO.getScore_Q3();
        this.score_Q4 = userPersonalDTO.getScore_Q4();
    }


}
