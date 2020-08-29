package domain.movie;

import exception.EmptyValueException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 映画 エンティティ
 */
@Getter
@ToString
@EqualsAndHashCode(of = "movieId") // 映画IDだけでequalsで比較する
public class Movie {
    // 映画ID
    private final MovieId movieId;
    // タイトル
    private String title;
    // 3D
    private boolean is3D;

    public Movie(String title, boolean is3D) throws EmptyValueException {
        this(new MovieId(), title, is3D);
    }

    public Movie(MovieId movieId, String title, boolean is3D) throws EmptyValueException {
        if (movieId == null) {
            throw new EmptyValueException("映画IDは必須です");
        }
        this.movieId = movieId;
        changeTitle(title);
        changeIs3D(is3D);
    }

    public void changeTitle(String title) throws EmptyValueException {
        if (title == null || title.isEmpty()) {
            throw new EmptyValueException("タイトルは必須でs");
        }
        this.title = title;
    }

    public void changeIs3D(boolean is3D) {
        this.is3D = is3D;
    }
}
