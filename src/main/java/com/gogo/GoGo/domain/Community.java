package com.gogo.GoGo.domain;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.StringTokenizer;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted = false")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long commentId;

    @Min(1)
    @Max(6)
    private Long placeId;

    private String gender;

    private String title;

    private String content;

    private LocalDate createdTime;

    private LocalDate startDate;

    private LocalDate endDate;

    //TODO: 해시태그 처리
    private String tags;

    @ColumnDefault("0")
    @Min(0)
    private Integer heart;

    private String createdBy;

    @ColumnDefault("0") // 이 값이 true가 되면 삭제가 되었다 간주하고 repository에서 삭제됨
    private boolean deleted;



    public void set(CommunityDto dto) {

        if(dto.getGender() != null){
            this.setGender(dto.getGender());
        }
        if(dto.getPlaceId() != null){
            this.setPlaceId(dto.getPlaceId());
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
