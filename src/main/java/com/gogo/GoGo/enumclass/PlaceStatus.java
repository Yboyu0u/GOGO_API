package com.gogo.GoGo.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlaceStatus {

    SEOUL(0,"서울"),
    GYEONGGI(1,"경기"),
    CHUNG(2,"충청도"),
    GYEONGSANG(3,"경상도"),
    JEOLLA(4,"전라도"),
    GANGWON(5,"강원도"),
    JEJU(6,"제주도"),
    NOTHING(7,"상관없음");



    private Integer id;
    private String title;
}
