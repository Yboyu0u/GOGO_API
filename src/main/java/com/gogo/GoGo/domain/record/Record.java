package com.gogo.GoGo.domain.record;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gogo.GoGo.domain.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"user"})
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String createdBy;

    private LocalDateTime createdTime;

    @NotNull
    @ColumnDefault("0")
    private boolean hidden;

    @NotNull
    @ColumnDefault("0")
    private boolean deleted;

    @ManyToOne
    @JsonBackReference
    private User user;

}
