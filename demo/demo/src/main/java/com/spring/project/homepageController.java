package com.spring.project;

import com.spring.project.models.Movie;
import com.spring.project.models.MovieDTO;
import com.spring.project.services.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class homepageController {

    @Autowired
    private MoviesRepository repo;

    @GetMapping("/")
    public String homepage(Model model) { // Render homepage
        List<Movie> movies = repo.findAll();
        model.addAttribute("movies", movies);
        return "homepage";
    } // homepage

    @GetMapping("/confirm")
    public String confirm() { return "confirmation";} // confirm

    @GetMapping("/create")
    public String create(Model model) { // Render create page
        MovieDTO movieDTO = new MovieDTO();
        model.addAttribute("movieDTO", movieDTO);
        return "create";
    } // create

    @GetMapping("/adminview")
    public String adminView() {
        return "adminView";
    } // adminView

    @GetMapping("/managemovies")
    public String manageMovies() {
        return "manageMovies";
    } // manageMovies

} // homepageController

