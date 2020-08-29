package domain.member;

import exception.ApplicationException;
import exception.EmptyValueException;
import exception.LengthException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * 会員No 値オブジェクト
 */
@Getter
@EqualsAndHashCode
public class MemberNo {
    private final String value;

    public MemberNo() {
        this.value = UUID.randomUUID().toString();
    }

    public MemberNo(String value) throws ApplicationException {
        if (value == null || value.isEmpty()) {
            throw new EmptyValueException("会員番号は必須です");
        }
        if (value.length() > 36) {
            throw new LengthException("会員番号は36桁以内で指定してください");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
