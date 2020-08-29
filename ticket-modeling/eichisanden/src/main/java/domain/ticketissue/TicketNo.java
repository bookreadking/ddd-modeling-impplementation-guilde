package domain.ticketissue;

import exception.ApplicationException;
import exception.EmptyValueException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * チケットNo 値オブジェクト
 */
@Getter
@EqualsAndHashCode
public class TicketNo {
    private final String value;

    public TicketNo() {
        this.value = UUID.randomUUID().toString();
    }

    public TicketNo(String value) throws ApplicationException {
        if (value == null || value.isEmpty()) {
            throw new EmptyValueException("チケットNoは必須です");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
