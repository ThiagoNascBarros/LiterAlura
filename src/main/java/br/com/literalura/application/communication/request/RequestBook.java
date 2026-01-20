package br.com.literalura.application.communication.request;

import br.com.literalura.domain.entities.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestBook(@JsonProperty String title,
                          @JsonProperty List<Author> authors,
                          @JsonProperty List<String> languages,
                          @JsonProperty Double download_count) {
}
