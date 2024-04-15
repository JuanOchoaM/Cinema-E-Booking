package com.spring.project.controllers;

import java.security.Principal;
import java.util.List;

import com.spring.project.models.MovieDTO;
import com.spring.project.services.MovieServices;
import com.spring.project.services.UserRepository;
import com.spring.project.users.User;
import com.spring.project.users.userinfo.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import com.spring.project.models.Movie;
import com.spring.project.services.MoviesRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class homepageController {
    @Autowired
    private MoviesRepository repo; // Reference to movie repository interface

    @Autowired
    private UserRepository userRepo; // Reference to user repository interface

    @Autowired
    private MovieServices movieService; // Reference to movie services interface

    @GetMapping("")
    public String homepage(Model model, Principal principal) { // Render homepage
        List<Movie> movies = repo.findAll();
        model.addAttribute("movies", movies);

        if (principal != null && userRepo.findByEmail(principal.getName()).getRole() == Role.ROLE_ADMIN)
        {
            return "adminView";
        }

        return "homepage";
    } // homepage
    
    // I AM ADDING THIS SO I CAN WORK ON IT LOCALLY
    @GetMapping("/admin/")
    public String adminView(Model model) {
        List<Movie> movies = repo.findAll();
        model.addAttribute("movies", movies);
        return "adminView";
    } // adminView

    @GetMapping("/admin/managemovies")
    public String manageMovies() {
        return "manageMovies";
    } // manageMovies

    @GetMapping("/admin/editmovies")
    public String editMovies(Model model) {
        List<Movie> movies = repo.findAll();
        model.addAttribute("movies", movies);
        return "editmovies";
    } // editMovies

    @GetMapping("/admin/addschedule")
    public String addSchedule() {
        return "addschedule";
    } // addschedule

    @GetMapping("/admin/manageusers")
    public String manageUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "manageusers";
    } // manageusers

    @GetMapping("/admin/create") // create get
    public String create(Model model) { // Render create page
        MovieDTO movieDTO = new MovieDTO();
        model.addAttribute("movieDTO", movieDTO);
        return "create";
    } // create

    @PostMapping("/admin/create") // create post
    public String createMovie(
            @Valid @ModelAttribute MovieDTO movieDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {return "/admin/create";} // Return to create if form input error

        // Post form input to DB
        Movie movie = new Movie();
        movie.setMovieTitle(movieDTO.getMovieTitle());
        movie.setCast(movieDTO.getCast());
        movie.setDirector(movieDTO.getDirector());
        movie.setProducer(movieDTO.getProducer());
        movie.setRating(movieDTO.getRating());
        movie.setReviews(movieDTO.getReviews());
        movie.setSynop(movieDTO.getSynop());
        movie.setTrailerPhoto(movie.getTrailerPhoto());
        movie.setTrailerVideo(movieDTO.getTrailerVideo());
        movie.setNowPlaying(movieDTO.getNowPlaying());

        repo.save(movie);

        return "redirect:/admin/"; // redirect home
    } // create

    @GetMapping("/search")
    public String search(@Param("keyword") String keyword, Model model) {
        List<Movie> searchResult = movieService.search(keyword);

        if(searchResult.isEmpty()) {
            keyword = "No results matching " + keyword;
        } else {
            keyword = "Results for " + keyword;
        } // else

        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", keyword);
        model.addAttribute("searchResult", searchResult); // Pass search results to front end
        return "searchresult";
    } // search

    @GetMapping("/admin/promos")
    public String promos() { return "promos"; } // promos

}

