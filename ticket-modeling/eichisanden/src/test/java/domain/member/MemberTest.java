package domain.member;

import exception.ApplicationException;
import exception.RangeException;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class MemberTest {

    @Test
    public void ノーマルケース() throws ApplicationException {
        var member = new Member("テスト太郎", LocalDate.of(2020, 1, 1));
        assertThat(member.getMemberNo(), notNullValue());
        assertThat(member.getName(), is("テスト太郎"));
        assertThat(member.getBirthDay(), is(LocalDate.of(2020, 1, 1)));
    }

    @Test(expected = RangeException.class)
    public void 誕生日に未来日付を指定したらエラーになること() throws ApplicationException {
        new Member("テスト太郎", LocalDate.now().plus(Period.ofDays(1)));
    }

    @Test
    public void testEquals() throws ApplicationException {
        var member1 = new Member(new MemberNo("a"), "abc", LocalDate.of(2000, 1, 1));
        var member2 = new Member(new MemberNo("a"), "def", LocalDate.of(2001, 2, 2));
        assertThat("属性が違くても会員番号が同じならtrueを返すこと", member1.equals(member2), is(true));
    }

    @Test
    public void 値の変更の確認() throws ApplicationException {
        var member1 = new Member("init", LocalDate.now());
        member1.changeName("changed");
        member1.changeBirthDay(LocalDate.of(2019, 12, 31));
        assertThat(member1.getName(), is("changed"));
        assertThat(member1.getBirthDay(), is(LocalDate.of(2019, 12, 31)));
    }
}