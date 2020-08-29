package domain.member;

import exception.ApplicationException;
import exception.LengthException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MemberNoTest {
    @Test
    public void 自動発番のテスト() {
        var memberNo1 = new MemberNo();
        assertThat("36桁であること", memberNo1.getValue().length(), is(36));
        var memberNo2 = new MemberNo();
        assertThat("36桁であること", memberNo2.getValue().length(), is(36));

        assertThat("違う値が発番されること", memberNo1, not(memberNo2));
    }

    @Test
    public void 手動設定のテスト() throws Exception {
        var memberNo1 = new MemberNo("1");
        assertThat("値を指定して生成できること", memberNo1.getValue(), is("1"));

    }

    @Test(expected = LengthException.class)
    public void 桁数が36を超える場合例外がスローされること() throws ApplicationException {
        MemberNo memberNo2 = new MemberNo("1234567890123456789012345678901234567");
    }
}
