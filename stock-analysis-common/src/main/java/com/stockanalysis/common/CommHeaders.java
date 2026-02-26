package com.stockanalysis.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommHeaders {
    @JsonProperty("api-id")
    private String apiId;
    private String authorization;
    @JsonProperty("cont-yn")
    private String contYn;
    @JsonProperty("next-key")
    private String nextKey;
}
