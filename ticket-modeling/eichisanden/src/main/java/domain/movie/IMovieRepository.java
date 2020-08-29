package domain.movie;

import java.util.List;

/**
 * 映画 リポジトリ
 */
public interface IMovieRepository {
    List<Movie> findAll();

    Movie find(MovieId movieId);
}
