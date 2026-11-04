package service;

import model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {

    private final List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Movie> searchMovies(String name) {

        List<Movie> result = new ArrayList<>();

        for (Movie movie : movies) {

            if (movie.getMovieName()
                    .toLowerCase()
                    .contains(name.toLowerCase())) {

                result.add(movie);
            }
        }

        return result;
    }
}