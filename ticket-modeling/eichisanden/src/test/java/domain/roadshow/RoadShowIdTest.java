package domain.roadshow;

import exception.ApplicationException;
import exception.LengthException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RoadShowIdTest {
    @Test
    public void 自動発番のテスト() {
        var roadShowId1 = new RoadShowId();
        assertThat(roadShowId1.getValue().length(), is(36));
        var roadShowId2 = new RoadShowId();
        assertThat(roadShowId2.getValue().length(), is(36));

        assertThat("違う値が発番されること", roadShowId1.equals(roadShowId2), is(false));
    }

    @Test
    public void 手動発番のテスト() throws ApplicationException {
        var roadShowId = new RoadShowId("1");
        assertThat(roadShowId.getValue(), is("1"));
    }

    @Test(expected = LengthException.class)
    public void 桁数が36桁を超えたらエラーになること() throws ApplicationException {
        new RoadShowId("1234567890123456789012345678901234567");
    }
}