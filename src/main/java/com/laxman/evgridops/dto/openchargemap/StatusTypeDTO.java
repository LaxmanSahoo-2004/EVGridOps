package com.laxman.evgridops.dto.openchargemap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusTypeDTO {

    @JsonProperty("IsOperational")
    private Boolean isOperational;

    @JsonProperty("Title")
    private String title;
}