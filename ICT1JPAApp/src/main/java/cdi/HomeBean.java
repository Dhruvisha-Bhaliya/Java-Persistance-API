/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import ejb.MovieBeanLocal;
import entity.Movie;
import entity.Theater;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author DELL
 */
@Named(value = "home")
@SessionScoped
public class HomeBean implements Serializable {

    @EJB
    MovieBeanLocal mbl;

    //movies operations
    private Collection<Movie> movies;
    private String mName;
    private Movie nMovie;

    //assignment operations
    private Collection<Theater> theaters;
    private int mId;
    private int tId;

    public HomeBean(){
        movies = new ArrayList();
        theaters = new ArrayList<>();
    }
    
    public String addMovie() {
        Movie movie = new Movie();
        movie.setMoviename(mName);
        mbl.addMovie(movie);
        return "AllMovies.jsf";
    }

    public MovieBeanLocal getMbl() {
        return mbl;
    }

    public void setMbl(MovieBeanLocal mbl) {
        this.mbl = mbl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Movie getnMovie() {
        return nMovie;
    }

    public void setnMovie(Movie nMovie) {
        this.nMovie = nMovie;
    }

    public Collection<Movie> getMovies() {
        movies = new ArrayList<>();
        movies = mbl.getAllMovies();
        System.out.println(movies.toString());
        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }

    public void deleteMovie(int mId) {
        mbl.deleteMovie(mId);
        getMovies();
    }

    public String addMovieToTheater() {
        mbl.assignMovieToTheater(mId, tId);
        return "index.jsf";
    }

    public Collection<Theater> getTheaters() {
        theaters = mbl.getAllTheaters();
        return theaters;
    }

    public void setTheaters(Collection<Theater> theaters) {
        this.theaters = theaters;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }
    
    public void deleteMovieFromTheater(int mId,int tId){
        mbl.deleteMovieFromTheater(mId, tId);
        movies = getMovies();
    }
        
}
