package com.laxman.evgridops.dto.openchargemap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenChargeMapDTO {

    @JsonProperty("ID")
    private Integer id;

    @JsonProperty("AddressInfo")
    private AddressInfoDTO addressInfo;

    @JsonProperty("StatusType")
    private StatusTypeDTO statusType;

    @JsonProperty("Connections")
    private List<ConnectionDTO> connections;
}