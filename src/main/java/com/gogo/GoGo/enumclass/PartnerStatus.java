package com.gogo.GoGo.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartnerStatus {

    ONLYSAME(0,"동성만"),
    NOTHING(1,"상관없음");

    private Integer id;
    private String title;
}
