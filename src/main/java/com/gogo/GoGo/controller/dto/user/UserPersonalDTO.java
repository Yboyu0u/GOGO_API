package com.gogo.GoGo.controller.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPersonalDTO {

    private int score_Q1;
    private int score_Q2;
    private int score_Q3;
    private int score_Q4;

}