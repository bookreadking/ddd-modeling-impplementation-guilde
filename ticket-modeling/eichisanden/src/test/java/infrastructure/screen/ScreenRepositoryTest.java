package infrastructure.screen;

import domain.screen.IScreenRepository;
import domain.screen.Screen;
import domain.screen.ScreenNo;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ScreenRepositoryTest {

    @Test
    public void findAll() {
        IScreenRepository repository = new ScreenRepository();
        assertThat(repository.findAll().size(), is(11));
    }

    @Test
    public void find() {
        IScreenRepository repository = new ScreenRepository();
        assertThat(repository.find(ScreenNo.E), is(new Screen(ScreenNo.E, 177)));
    }
}