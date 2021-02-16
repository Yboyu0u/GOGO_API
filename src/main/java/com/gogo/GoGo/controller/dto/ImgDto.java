package com.gogo.GoGo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgDto {

    private MultipartFile img;
}
