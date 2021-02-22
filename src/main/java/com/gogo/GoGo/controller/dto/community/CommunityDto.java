package com.gogo.GoGo.controller.dto.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityDto {

    private String title;

    private String content;

    private LocalDate startDate;

    private LocalDate endDate;
}
