package dev.marshallBits.estim.controllers;

import dev.marshallBits.estim.models.Game;
import dev.marshallBits.estim.models.ReviewType;
import dev.marshallBits.estim.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable Long id) {
        return gameService.findById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> searchGames(@RequestParam String name) {
        return gameService.searchByName(name);
    }

    @GetMapping("/review/{reviewType}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByReview(@PathVariable ReviewType reviewType) {
        return gameService.findByReviewType(reviewType);
    }

    @GetMapping("/genre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByGenre(@PathVariable String genre) {
        return gameService.findByGenre(genre);
    }

    @GetMapping("/developer/{developer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByDeveloper(@PathVariable String developer) {
        return gameService.findByDeveloper(developer);
    }

    @GetMapping("/top-rated")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getTopRatedGames() {
        return gameService.findAllOrderByMetacriticScore();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game updateGame(@PathVariable Long id, @RequestBody Game gameDetails) {
        return gameService.updateGame(id, gameDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}
