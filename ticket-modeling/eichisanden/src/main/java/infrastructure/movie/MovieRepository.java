package infrastructure.movie;

import domain.movie.IMovieRepository;
import domain.movie.Movie;
import domain.movie.MovieId;
import exception.ApplicationException;

import java.util.ArrayList;
import java.util.List;

/**
 * 映画 リポジトリ
 */
public class MovieRepository implements IMovieRepository {
    List<Movie> list;

    public MovieRepository() throws ApplicationException {
        this.list = new ArrayList<>();
        list.add(new Movie(new MovieId("1"), "2分の1の魔法", false));
        list.add(new Movie(new MovieId("2"), "ブックスマート 卒業前夜のパーティーデビュー", false));
        list.add(new Movie(new MovieId("3"), "ポルトガル、夏の終わり", false));
        list.add(new Movie(new MovieId("4"), "弱虫ペダル", false));
        list.add(new Movie(new MovieId("5"), "ジェクシー！　スマホを変えただけなのに", false));
        list.add(new Movie(new MovieId("6"), "ゼロ・グラビティ", true));
    }

    @Override
    public List<Movie> findAll() {
        return list;
    }

    @Override
    public Movie find(MovieId movieId) {
        return list.stream().filter(e -> e.getMovieId().equals(movieId)).findFirst().orElse(null);
    }
}
