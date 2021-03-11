package com.gogo.GoGo.domain.community;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gogo.GoGo.controller.dto.community.CommunityDto;
import com.gogo.GoGo.domain.Place;
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
@ToString(exclude = {"user"})
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonBackReference
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PartnerStatus partner;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdTime;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Min(0)
    @ColumnDefault("0")
    private Integer heart;

    @NotNull
    private String createdBy;

    @NotNull
    @ColumnDefault("0") // 이 값이 true가 되면 삭제가 되었다 간주하고 repository에서 삭제됨
    private boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    @JsonManagedReference
    private List<CommunityComment> communityCommentList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    @JsonManagedReference
    private List<CommunityHeart> communityHeartList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    @JsonManagedReference
    private List<Place> placeList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    @JsonManagedReference
    private List<Tag> tagList;






    public void set(CommunityDto dto) {

        if(dto.getPartner() != null){
            this.setPartner(dto.getPartner());
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
