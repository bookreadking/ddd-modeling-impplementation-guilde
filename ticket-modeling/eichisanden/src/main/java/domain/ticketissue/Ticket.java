package domain.ticketissue;

import exception.EmptyValueException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * チケット エンティティ
 */
@Getter
@EqualsAndHashCode(of = "ticketNo")
@ToString
public class Ticket {
    // チケットNo
    private final TicketNo ticketNo;
    // チケット種類
    private TicketType ticketType;
    // 料金
    private BigDecimal charge;

    public Ticket(TicketType ticketType, BigDecimal charge) throws EmptyValueException {
        this.ticketNo = new TicketNo();
        changeTicketType(ticketType);
        changeCharge(charge);
    }

    public void changeTicketType(TicketType ticketType) throws EmptyValueException {
        if (ticketType == null) {
            throw new EmptyValueException("チケットNoは必須です");
        }
        this.ticketType = ticketType;
    }

    public void changeCharge(BigDecimal charge) throws EmptyValueException {
        if (charge == null) {
            throw new EmptyValueException("料金は必須です");
        }
        this.charge = charge;
    }
}
