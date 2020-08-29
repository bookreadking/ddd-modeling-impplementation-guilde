package infrastructure.member;

import domain.member.IMemberRepository;
import domain.member.MemberNo;
import exception.ApplicationException;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class MemberRepositoryTest {

    @Test
    public void test() throws ApplicationException {
        IMemberRepository memberRepository = new MemberRepository();

        var list = memberRepository.findAll();
        assertThat(list.size(), is(4));

        var memberNo = new MemberNo("1");
        var member = memberRepository.find(memberNo);
        assertThat(member.getMemberNo(), is(memberNo));
        assertThat(member.getName(), is("山田太郎"));
        assertThat(member.getBirthDay(), is(LocalDate.of(1976, 2, 23)));
    }

    @Test
    public void 存在しないキーで検索した場合nullが返ること() throws ApplicationException {
        IMemberRepository memberRepository = new MemberRepository();

        var memberNo = new MemberNo("wrong_member_no");
        var member = memberRepository.find(memberNo);

        assertThat(member, nullValue());
    }

}