package com.gogo.GoGo.domain.community;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gogo.GoGo.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"user","community"}) //연관관계 관련된 변수는 처리
public class CommunityHeart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JsonBackReference
    private Community community;

}
