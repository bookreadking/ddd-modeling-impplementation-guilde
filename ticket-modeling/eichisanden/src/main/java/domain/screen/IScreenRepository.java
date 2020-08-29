package domain.screen;

import java.util.List;

/**
 * スクリーン リポジトリ
 */
public interface IScreenRepository {
    List<Screen> findAll();

    Screen find(ScreenNo screenNo);
}
