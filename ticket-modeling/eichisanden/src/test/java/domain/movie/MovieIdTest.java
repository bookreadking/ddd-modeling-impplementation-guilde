package domain.movie;

import exception.ApplicationException;
import exception.LengthException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MovieIdTest {

    @Test
    public void 自動発番のテスト() {
        var movieId1 = new MovieId();
        assertThat("36桁であること", movieId1.getValue().length(), is(36));
        var movieId2 = new MovieId();
        assertThat("36桁であること", movieId2.getValue().length(), is(36));

        assertThat("違う値が発番されること", movieId1, not(movieId2));
    }

    @Test
    public void 手動設定のテスト() throws Exception {
        var movieId1 = new MovieId("1");
        assertThat("値を指定して生成できること", movieId1.getValue(), is("1"));

    }

    @Test(expected = LengthException.class)
    public void 桁数が36を超える場合例外がスローされること() throws ApplicationException {
        new MovieId("1234567890123456789012345678901234567");
    }
}