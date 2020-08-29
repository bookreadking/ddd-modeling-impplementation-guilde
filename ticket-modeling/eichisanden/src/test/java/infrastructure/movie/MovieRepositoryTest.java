package infrastructure.movie;

import domain.movie.IMovieRepository;
import domain.movie.MovieId;
import exception.ApplicationException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MovieRepositoryTest {

    @Test
    public void findAll() throws ApplicationException {
        IMovieRepository repository = new MovieRepository();
        assertThat(repository.findAll().size(), is(6));
    }

    @Test
    public void find() throws ApplicationException {
        IMovieRepository repository = new MovieRepository();
        var movieId = new MovieId("6");
        var movie = repository.find(movieId);
        assertThat(movie.getMovieId(), is(movieId));
        assertThat(movie.getTitle(), is("ゼロ・グラビティ"));
        assertThat(movie.is3D(), is(true));
    }
}