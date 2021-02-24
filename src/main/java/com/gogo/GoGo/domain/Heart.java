package com.gogo.GoGo.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString(exclude = {"user"}) //연관관계 관련된 변수는 처리
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // N : 1 (자기 자신 기준)
//    @ManyToOne
    private Long userId; //userId

    private Long communityId;

}
