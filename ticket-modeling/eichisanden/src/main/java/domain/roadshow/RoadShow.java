package domain.roadshow;

import domain.movie.MovieId;
import domain.screen.ScreenNo;
import exception.ApplicationException;
import exception.EmptyValueException;
import exception.RangeException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * 上映 エンティティ
 */
@Getter
@EqualsAndHashCode(of = "roadShowId") // 映画IDだけでequalsで比較する
@ToString
public class RoadShow {
    // 上映ID
    private final RoadShowId roadShowId;
    // 映画ID
    private MovieId movieId;
    // スクリーンNo
    private ScreenNo screenNo;
    // 上映開始時間
    private LocalDateTime screeningStartTime;
    // 上映終了時間
    private LocalDateTime screeningEndTime;

    public RoadShow(RoadShowId roadShowId, MovieId movieId, ScreenNo screenNo, LocalDateTime screeningStartTime, LocalDateTime screeningEndTime) throws ApplicationException {
        if (roadShowId == null) {
            throw new EmptyValueException("上映IDは必須です");
        }
        this.roadShowId = roadShowId;
        changeMovieId(movieId);
        changeScreenNo(screenNo);
        changeScreenTime(screeningStartTime, screeningEndTime);
    }

    public void changeMovieId(MovieId movieId) throws EmptyValueException {
        if (movieId == null) {
            throw new EmptyValueException("映画IDは必須です");
        }
        this.movieId = movieId;
    }

    public void changeScreenNo(ScreenNo screenNo) throws EmptyValueException {
        if (screenNo == null) {
            throw new EmptyValueException("スクリーンはNo必須です");
        }
        this.screenNo = screenNo;
    }

    public void changeScreenTime(LocalDateTime start, LocalDateTime end) throws EmptyValueException, RangeException {
        if (start == null) {
            throw new EmptyValueException("上映開始時間は必須です");
        }
        if (end == null) {
            throw new EmptyValueException("上映開始時間は必須です");
        }
        if (start.compareTo(end) > 0) {
            throw new RangeException("上映開始時間＜上映終了時間である必要があります");
        }
        this.screeningStartTime = start;
        this.screeningEndTime = end;
    }

    public boolean isHoliday() {
        // 上映開始時間で判定する
        // 祝日は考慮しない
        return screeningStartTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                screeningStartTime.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    public boolean isWeekday() {
        return !isHoliday();
    }

    public boolean isLateShow() {
        return screeningStartTime.getHour() >= 20;
    }

    public boolean isEarlyShow() {
        return !isLateShow();
    }

    public boolean isMovieDay() {
        return screeningStartTime.getDayOfMonth() == 1;
    }
}
