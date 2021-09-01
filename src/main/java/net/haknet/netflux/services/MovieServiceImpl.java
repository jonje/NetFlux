package net.haknet.netflux.services;

import net.haknet.netflux.domain.Movie;
import net.haknet.netflux.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository repository;

    @Inject
    public MovieServiceImpl(final MovieRepository movieRepository) {
        this.repository = movieRepository;
    }

    @Override
    public Mono<Movie> getMovieById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return this.repository.findAll();
    }
}
