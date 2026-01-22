package br.com.literalura.application.communication.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponsetBook(@JsonProperty("title") String title,
                            @JsonProperty("authors") List<ResponseAuthor> authors,
                            @JsonProperty("languages") List<String> languages,
                            @JsonProperty("download_count") Double download_count) {
    @Override
    public String toString() {
        return String.format(
                """
                        ---------------------------
                        📖 Título: %s
                           Autor(es): %s
                           Idioma(s): %s
                           Downloads: %,.0f
                        ---------------------------""",
                title,
                authors.isEmpty() ? "Desconhecido" : authors,
                String.join(", ", languages),
                download_count
        );
    }
}

