package br.com.literalura.domain.entities;

import br.com.literalura.application.communication.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;

    private String language;

    private Double numberDownloads;

    public Book(ApiResponse res) {
        var names = res.results().get(0).authors().stream()
                .map(a -> new String(a.name()))
                .toList();

        this.title = res.results().get(0).title();
        this.author = new Author(res.results().get(0).authors().get(0));
        this.language = String.join(", " , res.results().get(0).languages());
        this.numberDownloads = res.results().get(0).download_count();
    }

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
                author.getName(),
                language,
                numberDownloads
        );
    }
}
