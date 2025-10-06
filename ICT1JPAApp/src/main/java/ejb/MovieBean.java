/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Movie;
import entity.Theater;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author DELL
 */
@Stateless
public class MovieBean implements MovieBeanLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addMovie(Movie movie) {
        em.persist(movie);
    }

    @Override
    public void deleteMovie(int id) {
        Movie movie = em.find(Movie.class, id);
        em.remove(movie);
    }

    @Override
    public void deleteTheater(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<Movie> getAllMovies() {
        Collection<Movie> movies = em.createQuery("SELECT m FROM Movie m").getResultList();
        return movies;
    }

    @Override
    public Collection<Theater> getAllTheaters() {
        Collection<Theater> theaters = em.createQuery("SELECT t FROM Theater t").getResultList();
        return theaters;
    }

    @Override
    public void assignMovieToTheater(int mId, int tId) {
        Movie m = em.find(Movie.class, mId);
        Theater t = em.find(Theater.class, tId);

        if (m != null && t != null) {
            // Initialize if null
            if (t.getMovies() == null) {
                t.setMovies(new ArrayList<>());
            }
            if (m.getTheaters() == null) {
                m.setTheaters(new ArrayList<>());
            }

            // Add relationship both ways
            if (!t.getMovies().contains(m)) {
                t.getMovies().add(m);
            }
            if (!m.getTheaters().contains(t)) {
                m.getTheaters().add(t);
            }

            // Persist owning side (Theater has @JoinTable)
            em.merge(t);
        }
    }

    @Override
    public void deleteMovieFromTheater(int mId, int tId) {
        Movie m = em.find(Movie.class, mId);
        Theater t = em.find(Theater.class, tId);

        if (t.getMovies().contains(m)) {

            Collection<Movie> movies = t.getMovies();
            movies.remove(m);
            t.setMovies(movies);
            Collection<Theater> theaters = m.getTheaters();
            theaters.remove(t);
            m.setTheaters(theaters);
            em.merge(m);
        }
    }
}
