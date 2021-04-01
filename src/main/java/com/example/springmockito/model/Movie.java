package com.example.springmockito.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class Movie {
    private final String title;

    @JsonCreator
    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
