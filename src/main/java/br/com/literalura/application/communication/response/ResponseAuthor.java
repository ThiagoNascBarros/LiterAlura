package br.com.literalura.application.communication.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseAuthor(@JsonProperty String name,
                             @JsonProperty("birth_year") Integer birthYear,
                             @JsonProperty("death_year") Integer deathYear) {
    @Override
    public String toString() {
        String lifespan = (birthYear != null || deathYear != null)
                ? String.format(" (%s - %s)",
                birthYear != null ? birthYear : "?",
                deathYear != null ? deathYear : "Presente")
                : "";
        return name + lifespan;
    }
}
