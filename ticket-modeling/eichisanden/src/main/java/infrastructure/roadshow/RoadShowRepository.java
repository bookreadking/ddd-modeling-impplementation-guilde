package infrastructure.roadshow;

import domain.movie.MovieId;
import domain.roadshow.IRoadShowRepository;
import domain.roadshow.RoadShow;
import domain.roadshow.RoadShowId;
import domain.screen.ScreenNo;
import exception.ApplicationException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 上映 リポジトリ
 */
public class RoadShowRepository implements IRoadShowRepository {
    private final List<RoadShow> list = new ArrayList<>();

    public RoadShowRepository() throws ApplicationException {
        // 3D
        list.add(new RoadShow(new RoadShowId("1"),
                new MovieId("6"),
                ScreenNo.A,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                LocalDateTime.of(2020, 1, 1, 12, 0)));
        // 休日
        list.add(new RoadShow(new RoadShowId("2"),
                new MovieId("1"),
                ScreenNo.B,
                LocalDateTime.of(2020, 1, 5, 13, 10),
                LocalDateTime.of(2020, 1, 5, 15, 20)));
        // レイトショー
        list.add(new RoadShow(new RoadShowId("3"),
                new MovieId("5"),
                ScreenNo.C,
                LocalDateTime.of(2020, 1, 1, 20, 0),
                LocalDateTime.of(2020, 1, 1, 23, 15)));
        // ノーマル
        list.add(new RoadShow(new RoadShowId("4"),
                new MovieId("2"),
                ScreenNo.D,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                LocalDateTime.of(2020, 1, 1, 12, 0)));

    }

    @Override
    public List<RoadShow> findAll() {
        return list;
    }

    @Override
    public RoadShow find(RoadShowId roadShowId) {
        return list.stream().filter(e -> e.getRoadShowId().equals(roadShowId)).findFirst().orElse(null);
    }
}
