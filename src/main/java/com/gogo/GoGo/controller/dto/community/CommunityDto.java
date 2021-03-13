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
    private Long boardId;

    @NotNull
    private String title;

    @NotNull
    private String content;


}
