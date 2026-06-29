package com.laxman.evgridops.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentImportDTO {

    private String name;
    private String status;
    private LocalDateTime createdAt;

}