package com.gogo.GoGo.domain;

import com.gogo.GoGo.controller.dto.community.CommunityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    private String tag;

    private Integer heart;



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
        if(dto.getTag() != null){
            this.setTag(dto.getTag());
        }

        //TODO: createdDate 처리
    }
}
