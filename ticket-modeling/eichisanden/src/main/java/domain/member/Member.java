package domain.member;

import exception.ApplicationException;
import exception.EmptyValueException;
import exception.LengthException;
import exception.RangeException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 会員 エンティティ
 */
@Getter
@EqualsAndHashCode(of = "memberNo") // 会員番号だけでequalsで比較する
@ToString
public class Member {
    // 会員番号
    private final MemberNo memberNo;
    // 氏名
    private String name;
    // 生年月日
    private LocalDate birthDay;

    public Member(String name, LocalDate birthDay) throws ApplicationException {
        this(new MemberNo(), name, birthDay);
    }

    public Member(MemberNo memberNo, String name, LocalDate birthDay) throws ApplicationException {
        if (memberNo == null) {
            throw new EmptyValueException("会員Noは必須です");
        }
        this.memberNo = memberNo;
        changeName(name);
        changeBirthDay(birthDay);
    }

    // TODO: nameとbirthdayを値オブジェクトにするかは悩みどころ

    public void changeName(String name) throws ApplicationException {
        if (name == null || name.isEmpty()) {
            throw new EmptyValueException("名前は必須です");
        }
        if (name.length() > 100) {
            throw new LengthException("名前は100バイト以内で入力してください: " + name);
        }
        this.name = name;
    }

    public void changeBirthDay(LocalDate birthDay) throws ApplicationException {
        if (birthDay == null) {
            throw new EmptyValueException("誕生日は必須です");
        }
        if (birthDay.compareTo(LocalDate.now()) > 0) {
            throw new RangeException("誕生日に未来日付は指定できません: " + birthDay.toString());
        }
        this.birthDay = birthDay;
    }
}
