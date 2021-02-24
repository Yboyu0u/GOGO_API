package com.gogo.GoGo.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"community"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //N : 1
    @ManyToOne
    @JsonBackReference
    private Community community;

    private Long userId;

    private String userName;

    private String content;

    private LocalDateTime createdTime;
}
