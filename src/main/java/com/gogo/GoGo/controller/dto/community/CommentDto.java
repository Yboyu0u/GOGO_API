package com.gogo.GoGo.controller.dto.community;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    @NotNull
    private Long communityId;

    @NotNull
    private String content;


    private Long toId;

}
