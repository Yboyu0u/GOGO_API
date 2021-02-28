package com.gogo.GoGo.controller.dto.community;

import com.gogo.GoGo.enumclass.PartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommunityDto {

    @NotNull
    private String places;

    @NotNull
    private PartnerStatus partner; //동성만, 상관 없음

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private String tags;
}
