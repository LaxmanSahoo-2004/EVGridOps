package com.laxman.evgridops.dto.openchargemap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionDTO {

    @JsonProperty("ConnectionType")
    private ConnectionTypeDTO connectionType;

    @JsonProperty("PowerKW")
    private Double powerKW;

    @JsonProperty("Quantity")
    private Integer quantity;
}