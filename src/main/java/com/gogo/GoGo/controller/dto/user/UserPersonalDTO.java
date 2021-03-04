package com.gogo.GoGo.controller.dto.user;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserPersonalDTO {

    private int score_Q1;
    private int score_Q2;
    private int score_Q3;
    private int score_Q4;

}