package com.gogo.GoGo.controller.dto.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommunityDto {

    private String places;

    private String gender; //동성만, 상관 없음

    private String title;

    private String content;

    private LocalDate startDate;

    private LocalDate endDate;

    private String tags;
}
