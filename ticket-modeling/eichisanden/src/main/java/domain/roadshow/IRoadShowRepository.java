package domain.roadshow;

import java.util.List;

/**
 * 上映 リポジトリ
 */
public interface IRoadShowRepository {
    List<RoadShow> findAll();

    RoadShow find(RoadShowId roadShowId);
}
