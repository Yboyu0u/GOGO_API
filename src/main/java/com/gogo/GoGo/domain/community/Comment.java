package com.gogo.GoGo.domain.community;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gogo.GoGo.domain.user.User;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String content;

    @NotNull
    private String createdBy;

    @NotNull
    private LocalDateTime createdTime;


    private Long to;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private User user;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private Community community;
}
