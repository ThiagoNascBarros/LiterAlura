package br.com.literalura.application.communication.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestAuthor(@JsonProperty String name,
                            @JsonProperty Integer birthYear,
                            @JsonProperty Integer deathYear) {
}
