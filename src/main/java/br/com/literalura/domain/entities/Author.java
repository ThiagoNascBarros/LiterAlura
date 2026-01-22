package br.com.literalura.domain.entities;

import br.com.literalura.application.communication.response.ResponseAuthor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private Integer birthYear;

    private Integer deathYear;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books;

    public Author(ResponseAuthor responseAuthor) {
        this.name = responseAuthor.name();
        this.birthYear = responseAuthor.birthYear();
        this.deathYear = responseAuthor.deathYear();
    }
}
