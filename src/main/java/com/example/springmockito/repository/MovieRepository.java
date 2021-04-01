package com.example.springmockito.repository;

import com.example.springmockito.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public abstract class MovieRepository implements ReactiveMongoRepository<Movie, String> {
    public abstract Flux<Movie> findByTitle(String title);
}
