package com.gogo.GoGo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gogo.GoGo.controller.dto.community.CommunityDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted = false")
@ToString(exclude = {"user"})
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonBackReference
    private User user;

    private String gender;


    private String title;


    private String content;


    private LocalDateTime createdTime;


    private LocalDate startDate;


    private LocalDate endDate;

    //TODO: 해시태그 처리
    private String tags;

    @Min(0)
    @ColumnDefault("0")
    private Integer heart;

    private String createdBy;

    @ColumnDefault("0") // 이 값이 true가 되면 삭제가 되었다 간주하고 repository에서 삭제됨
    private boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    @JsonManagedReference
    private List<Comment> commentList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    @JsonManagedReference
    private List<Heart> heartList;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    @JsonManagedReference
    private List<Place> placeList;






    public void set(CommunityDto dto) {

        if(dto.getGender() != null){
            this.setGender(dto.getGender());
        }
        if(dto.getTitle() != null){
            this.setTitle(dto.getTitle());
        }
        if(dto.getContent() != null){
            this.setContent(dto.getContent());
        }
        if(dto.getStartDate() != null){
            this.setStartDate(dto.getStartDate());
        }
        if(dto.getEndDate() != null){
            this.setEndDate(dto.getEndDate());
        }
    }
}
