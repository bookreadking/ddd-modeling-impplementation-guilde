package domain.roadshow;

import domain.movie.MovieId;
import domain.screen.ScreenNo;
import exception.ApplicationException;
import exception.EmptyValueException;
import exception.RangeException;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RoadShowTest {
    @Test
    public void ノーマルケース() throws ApplicationException {
        var roadShow = new RoadShow(new RoadShowId("1"),
                new MovieId("1"),
                ScreenNo.A,
                LocalDateTime.of(2020, 1, 1, 20, 10),
                LocalDateTime.of(2020, 1, 1, 22, 25));
        assertThat(roadShow.getRoadShowId().getValue(), is(new RoadShowId("1").getValue()));
    }

    @Test(expected = EmptyValueException.class)
    public void 必須エラーチェック() throws ApplicationException {
        new RoadShow(null,
                new MovieId("1"),
                ScreenNo.A,
                LocalDateTime.of(2020, 1, 1, 20, 10),
                LocalDateTime.of(2020, 1, 1, 22, 25));
    }

    @Test(expected = RangeException.class)
    public void 上映開始時間と終了時間の大小チェック() throws ApplicationException {
        new RoadShow(new RoadShowId("1"),
                new MovieId("1"),
                ScreenNo.A,
                LocalDateTime.of(2020, 1, 1, 22, 25),
                LocalDateTime.of(2020, 1, 1, 20, 10));
    }

    @Test
    public void 値の変更のテスト() throws ApplicationException {
        var roadShow = new RoadShow(new RoadShowId("1"),
                new MovieId("1"),
                ScreenNo.A,
                LocalDateTime.now(),
                LocalDateTime.now());
        roadShow.changeMovieId(new MovieId("2"));
        roadShow.changeScreenNo(ScreenNo.B);
        roadShow.changeScreenTime(
                LocalDateTime.of(2020, 1, 1, 0, 0),
                LocalDateTime.of(2020, 2, 2, 23, 59));
        assertThat(roadShow.getMovieId(), is(new MovieId("2")));
        assertThat(roadShow.getScreenNo(), is(ScreenNo.B));
        assertThat(roadShow.getScreeningStartTime(),
                is(LocalDateTime.of(2020, 1, 1, 0, 0)));
        assertThat(roadShow.getScreeningEndTime(),
                is(LocalDateTime.of(2020, 2, 2, 23, 59)));
    }

    @Test
    public void 比較のチェック() throws ApplicationException {
        var roadShow1 = new RoadShow(new RoadShowId("1"),
                new MovieId("1"),
                ScreenNo.A,
                LocalDateTime.of(2020, 1, 1, 20, 10),
                LocalDateTime.of(2020, 1, 1, 22, 25));
        var roadShow2 = new RoadShow(new RoadShowId("1"),
                new MovieId("2"),
                ScreenNo.B,
                LocalDateTime.of(2020, 1, 1, 20, 10),
                LocalDateTime.of(2020, 1, 1, 22, 25));
        assertThat("属性が異なっても上映IDが一致すればtrueを返す", roadShow1.equals(roadShow2), is(true));
    }
}