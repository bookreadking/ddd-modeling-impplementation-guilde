package domain.member;

import java.util.List;

/**
 * 会員 リポジトリ
 */
public interface IMemberRepository {
    List<Member> findAll();

    Member find(MemberNo memberNo);
}
