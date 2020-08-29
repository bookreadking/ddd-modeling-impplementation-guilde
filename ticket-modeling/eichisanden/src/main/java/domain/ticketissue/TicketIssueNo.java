package domain.ticketissue;

import exception.ApplicationException;
import exception.EmptyValueException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * 発券No 値オブジェクト
 */
@Getter
@EqualsAndHashCode
public class TicketIssueNo {
    private final String value;

    public TicketIssueNo() {
        value = UUID.randomUUID().toString();
    }

    public TicketIssueNo(String value) throws ApplicationException {
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
