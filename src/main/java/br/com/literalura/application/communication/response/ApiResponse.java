package br.com.literalura.application.communication.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        @JsonProperty("results") List<ResponsetBook> results
) {

    @Override
    public String toString() {
        return results.toString();
    }
}
