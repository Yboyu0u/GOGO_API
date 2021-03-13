package com.gogo.GoGo.domain.community;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.record.Tag;
import com.gogo.GoGo.domain.user.User;
import com.gogo.GoGo.enumclass.PartnerStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted = false")
@ToString(exclude = {"user","board"})
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String createdBy;

    @NotNull
    private LocalDateTime createdTime;

    @Min(0)
    @ColumnDefault("0")
    private Integer heart;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JsonBackReference
    private Board board;

    @NotNull
    @ColumnDefault("0")
    private boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    @JsonManagedReference
    private List<Comment> commentList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    @JsonManagedReference
    private List<Heart> heartList;



    public void set(CommunityDto dto) {

        if(dto.getTitle() != null){
            this.setTitle(dto.getTitle());
        }
        if(dto.getContent() != null){
            this.setContent(dto.getContent());
        }

    }
}
