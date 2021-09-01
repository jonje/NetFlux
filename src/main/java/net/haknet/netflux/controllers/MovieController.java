package net.haknet.netflux.controllers;

import net.haknet.netflux.domain.Movie;
import net.haknet.netflux.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@RestController
@RequestMapping("movies")
public class MovieController {
    private final MovieService movieService;

    @Inject
    public MovieController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    Flux<Movie> getAllMovies() {
        return this.movieService.getAllMovies();
    }

    @GetMapping("{id}")
    Mono<Movie> getMovieById(@PathVariable String id) {
        return this.movieService.getMovieById(id);
    }
}
