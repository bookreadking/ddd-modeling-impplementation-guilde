package infrastructure.roadshow;

import domain.movie.MovieId;
import domain.roadshow.IRoadShowRepository;
import domain.roadshow.RoadShowId;
import exception.ApplicationException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RoadShowRepositoryTest {

    @Test
    public void findAll() throws ApplicationException {
        IRoadShowRepository repository = new RoadShowRepository();
        assertThat(repository.findAll().size(), is(4));
    }

    @Test
    public void find() throws ApplicationException {
        IRoadShowRepository repository = new RoadShowRepository();
        var roadShowId = new RoadShowId("1");
        var roadShow = repository.find(roadShowId);
        assertThat(roadShow.getMovieId(), is(new MovieId("6")));
    }
}