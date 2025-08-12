package dev.marshallBits.estim.repositories;

import dev.marshallBits.estim.models.Game;
import dev.marshallBits.estim.models.ReviewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByReviewType(ReviewType reviewType);

    List<Game> findByGenre(String genre);

    List<Game> findByDeveloper(String developer);

    @Query("SELECT g FROM Game g WHERE g.name ILIKE %?1%")
    List<Game> findByNameContainingIgnoreCase(String name);

    @Query("SELECT g FROM Game g ORDER BY g.metacriticScore DESC")
    List<Game> findAllOrderByMetacriticScoreDesc();
}
