package dev.marshallBits.estim.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewType reviewType;

    @Column(length = 1000)
    private String description;

    private String developer;

    private String publisher;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(precision = 4, scale = 2)
    private BigDecimal price;

    private String genre;

    @Column(name = "metacritic_score")
    private Integer metacriticScore;

    @Column(name = "steam_reviews")
    private Integer steamReviews;
}
