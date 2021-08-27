package net.haknet.netflux.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.haknet.netflux.domain.Movie;
import net.haknet.netflux.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.inject.Inject;

@Slf4j
@Component
public class InitMovies implements CommandLineRunner {
    private final MovieRepository repository;

    @Inject
    public InitMovies(final MovieRepository repository) {
        this.repository = repository;
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("Cleaning up database");
        this.repository.deleteAll().thenMany(
                Flux.just("Hackers", "Live Free Die Hard", "Lord of the Rings", "Back to the Future", "Star Wars")
                        .map(Movie::new)
                        .flatMap(this.repository::save)
        ).subscribe(null, null, () ->{
            this.repository.findAll().subscribe(movie -> log.info("{} Loaded into Database", movie.getTitle()));
        });

    }
}
