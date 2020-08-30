package domain.ticketissue;

import domain.price.TicketPriceType;
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
    // 料金
    private BigDecimal charge;

    public Ticket(TicketPriceType ticketPriceType, BigDecimal charge) throws EmptyValueException {
        this.ticketNo = new TicketNo();
        changeCharge(charge);
    }

    public void changeCharge(BigDecimal charge) throws EmptyValueException {
        if (charge == null) {
            throw new EmptyValueException("料金は必須です");
        }
        this.charge = charge;
    }
}
