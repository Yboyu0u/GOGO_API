package com.gogo.GoGo.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"community","user"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private User user;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private Community community;

    @NotNull
    private String userName;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdTime;
}
