package dev.marshallBits.estim.services;

import dev.marshallBits.estim.models.Game;
import dev.marshallBits.estim.models.ReviewType;

import java.util.List;

public interface GameService {

    List<Game> findAll();

    Game findById(Long id);

    List<Game> searchByName(String name);

    List<Game> findByReviewType(ReviewType reviewType);

    List<Game> findByGenre(String genre);

    List<Game> findByDeveloper(String developer);

    List<Game> findAllOrderByMetacriticScore();

    Game createGame(Game game);

    Game updateGame(Long id, Game gameDetails);

    void deleteGame(Long id);
}
