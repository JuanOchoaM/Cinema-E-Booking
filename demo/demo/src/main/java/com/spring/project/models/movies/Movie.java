package com.spring.project.models.movies;

import jakarta.persistence.*;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movieTitle;
    private String cast;
    private String director;
    private String producer;
    private String synop;
    private String reviews;
    private String trailerPhoto;
    private String trailerVideo;
    private String rating;
    private String nowPlaying;
    private String category;
    private String childTicket;
    private String adultTicket;
    private String seniorTicket;


    public String getNowPlaying() {
        return nowPlaying;
    }

    public void setNowPlaying(String nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSynop() {
        return synop;
    }

    public void setSynop(String synop) {
        this.synop = synop;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getTrailerPhoto() {
        return trailerPhoto;
    }

    public void setTrailerPhoto(String trailerPhoto) {
        this.trailerPhoto = trailerPhoto;
    }

    public String getTrailerVideo() {
        return trailerVideo;
    }

    public void setTrailerVideo(String trailerVideo) {
        this.trailerVideo = trailerVideo;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChildTicket() {return childTicket;}
    public void setChildTicket(String childTicket) { this.childTicket = childTicket; }
    public String getAdultTicket() {return adultTicket;}
    public void setAdultTicket(String adultTicket) { this.adultTicket = adultTicket; }
    public String getSeniorTicket() {return seniorTicket;}
    public void setSeniorTicket(String seniorTicket) { this.seniorTicket = seniorTicket; }

} // Movie