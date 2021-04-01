package com.example.springmockito.controller;

import com.example.springmockito.model.Movie;
import com.example.springmockito.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MovieService movieService;

    @Test
    void getMovies_shouldRespondWith200() {
        webTestClient.get()
                .uri("/api/movies")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void getMovies_shouldRespondWith404_givenWrongUri() {
        webTestClient.get()
                .uri("/api/moviess")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void getMovies_shouldRespondWithEmptyList_givenServiceProvidesEmptyList() {
        // given
        Mockito.when(movieService.getMovies()).thenReturn(Flux.empty());

        // when
        webTestClient.get()
                .uri("/api/movies")
                .exchange()
                .expectStatus()
                .isOk();

        // then
        Mockito.verify(movieService).getMovies();
    }

    @Test
    void getMovies_shouldRespondWithList_givenServiceProvidesList() {
        // given
        var movies = Flux.just(new Movie("Star Wars"));
        Mockito.when(movieService.getMovies()).thenReturn(movies);

        // when
        var response = webTestClient.get()
                .uri("/api/movies")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<List<Movie>>() {
                })
                .returnResult()
                .getResponseBody();


        // then
        Mockito.verify(movieService).getMovies();
        assertThat(response).isEqualTo(movies);
    }
}
