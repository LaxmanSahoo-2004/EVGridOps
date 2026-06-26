package com.laxman.evgridops.dto.openchargemap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressInfoDTO {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("AddressLine1")
    private String addressLine1;

    @JsonProperty("Town")
    private String town;

    @JsonProperty("StateOrProvince")
    private String stateOrProvince;

    @JsonProperty("Postcode")
    private String postcode;

    @JsonProperty("Latitude")
    private Double latitude;

    @JsonProperty("Longitude")
    private Double longitude;
}