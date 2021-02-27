package com.gogo.GoGo.controller.dto.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchTagDto {
    private String tag;
}