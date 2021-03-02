package com.gogo.GoGo.controller.dto.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateDto {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
