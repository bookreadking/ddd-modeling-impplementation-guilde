package infrastructure.member;

import domain.member.IMemberRepository;
import domain.member.Member;
import domain.member.MemberNo;
import exception.ApplicationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * メンバー リポジトリ
 */
public class MemberRepository implements IMemberRepository {
    private final List<Member> list = new ArrayList<>();

    public MemberRepository() throws ApplicationException {
        list.add(new Member(new MemberNo("1"), "山田太郎", LocalDate.of(1976, 2, 23)));
        list.add(new Member(new MemberNo("2"), "山田花子", LocalDate.of(1974, 10, 11)));
        list.add(new Member(new MemberNo("3"), "山田一郎", LocalDate.of(2010, 4, 21)));
        list.add(new Member(new MemberNo("4"), "山田次郎", LocalDate.of(2012, 10, 2)));
    }

    @Override
    public List<Member> findAll() {
        return list;
    }

    @Override
    public Member find(MemberNo memberNo) {
        return list.stream().filter(e -> e.getMemberNo().equals(memberNo)).findFirst().orElse(null);
    }
}
