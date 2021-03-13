package com.gogo.GoGo.controller.dto.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagDto {

    private String tag;
}
