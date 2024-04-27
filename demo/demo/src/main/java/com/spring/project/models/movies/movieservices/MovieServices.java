package com.spring.project.models.movies.movieservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.models.movies.Movie;

import java.util.List;

@Service
public class MovieServices {

    @Autowired
    private MoviesRepository repo;

    public List<Movie> search(String keyword) {
        return repo.search(keyword);
    } // search
    public Movie findById(int id) {
        return repo.findById(id).orElse(null); // Fetch movie by ID, handle null as needed
    }

} // MovieServices
