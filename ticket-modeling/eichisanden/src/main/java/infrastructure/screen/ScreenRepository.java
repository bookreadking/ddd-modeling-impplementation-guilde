package infrastructure.screen;

import domain.screen.IScreenRepository;
import domain.screen.Screen;
import domain.screen.ScreenNo;

import java.util.ArrayList;
import java.util.List;

/**
 * スクリーン リポジトリ
 */
public class ScreenRepository implements IScreenRepository {
    private final List<Screen> list = new ArrayList<>();

    public ScreenRepository() {
        list.add(new Screen(ScreenNo.A, 384));
        list.add(new Screen(ScreenNo.B, 303));
        list.add(new Screen(ScreenNo.C, 180));
        list.add(new Screen(ScreenNo.D, 177));
        list.add(new Screen(ScreenNo.E, 177));
        list.add(new Screen(ScreenNo.F, 226));
        list.add(new Screen(ScreenNo.G, 279));
        list.add(new Screen(ScreenNo.H, 98));
        list.add(new Screen(ScreenNo.I, 170));
        list.add(new Screen(ScreenNo.J, 77));
        list.add(new Screen(ScreenNo.K, 175));
    }

    @Override
    public List<Screen> findAll() {
        return list;
    }

    @Override
    public Screen find(ScreenNo screenNo) {
        return list.stream().filter(e -> e.getScreenNo().equals(screenNo)).findFirst().orElse(null);
    }
}
