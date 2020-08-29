package domain.movie;

import exception.ApplicationException;
import exception.EmptyValueException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class MovieTest {
    @Test
    public void ノーマルケース() throws EmptyValueException {
        var movie = new Movie("Star Wars EpisodeIV", false);
        assertThat(movie.getMovieId(), notNullValue());
        assertThat(movie.getTitle(), is("Star Wars EpisodeIV"));
        assertThat(movie.is3D(), is(false));
    }

    @Test(expected = EmptyValueException.class)
    public void タイトルが空だったらエラー() throws EmptyValueException {
        var movie = new Movie(null, true);
    }

    @Test
    public void testEquals() throws ApplicationException {
        var movie1 = new Movie(new MovieId("1"), "abc", false);
        var movie2 = new Movie(new MovieId("1"), "def", true);
        assertThat("属性が違くても映画IDが同じならtrueを返すこと", movie1.equals(movie2), is(true));
    }

    @Test
    public void 値の変更のテスト() throws EmptyValueException {
        var movie = new Movie("init", false);
        movie.changeTitle("changed");
        movie.changeIs3D(true);
        assertThat(movie.getTitle(), is("changed"));
        assertThat(movie.is3D(), is(true));
    }
}