package domain.roadshow;

import exception.EmptyValueException;
import exception.LengthException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * 上映ID 値オブジェクト
 */
@Getter
@EqualsAndHashCode
public class RoadShowId {
    private final String value;

    public RoadShowId() {
        this.value = UUID.randomUUID().toString();
    }

    public RoadShowId(String value) throws exception.ApplicationException {
        if (value == null || value.isEmpty()) {
            throw new EmptyValueException("映画IDは必須です");
        }
        if (value.length() > 36) {
            throw new LengthException("映画IDは36桁までです");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
