package br.com.literalura.application.ports.inbound.repository;

import br.com.literalura.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, UUID> {
    Author findByName(String name);

    @Query("select a from author a where a.birthYear <= :birthYear and (a.deathYear >= :deathYear or a.deathYear is null)")
    List<Author> findByBirthYearAndDeathYearAfter(Integer birthYear, Integer deathYear);
}
