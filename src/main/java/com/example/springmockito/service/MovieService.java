package com.example.springmockito.service;

import com.example.springmockito.model.Movie;
import com.example.springmockito.repository.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Flux<Movie> getMovies() {
        return movieRepository.findAll();
    }
}
