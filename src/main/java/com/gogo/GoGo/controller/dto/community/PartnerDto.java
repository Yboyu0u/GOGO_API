package com.gogo.GoGo.controller.dto.community;

import com.gogo.GoGo.enumclass.PartnerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDto {

    @NotNull
    @Enumerated(EnumType.STRING)
    private PartnerStatus partner;
}
