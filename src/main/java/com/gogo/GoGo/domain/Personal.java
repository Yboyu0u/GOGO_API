package com.gogo.GoGo.domain;

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
public class Personal {

    @Id
    @GeneratedValue
    @Column(name = "PERSONAL_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private int Q1;

    private int Q2;

    private int Q3;

    private int Q4;

    public void set(UserPersonalDTO userPersonalDTO) {
        this.Q1 = userPersonalDTO.getQ1();
        this.Q2 = userPersonalDTO.getQ2();
        this.Q3 = userPersonalDTO.getQ3();
        this.Q4 = userPersonalDTO.getQ4();
    }
}
