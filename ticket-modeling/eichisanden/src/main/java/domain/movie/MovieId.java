package domain.movie;

import exception.EmptyValueException;
import exception.LengthException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * 映画ID 値オブジェクト
 */
@EqualsAndHashCode
@Getter
public class MovieId {
    private final String value;

    public MovieId() {
        this.value = UUID.randomUUID().toString();
    }

    public MovieId(String value) throws exception.ApplicationException {
        if (value == null || value.isEmpty()) {
            throw new EmptyValueException("映画IDは必須です");
        }
        if (value.length() > 36) {
            throw new LengthException("映画IDは36桁以内で指定してください");
        }
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
