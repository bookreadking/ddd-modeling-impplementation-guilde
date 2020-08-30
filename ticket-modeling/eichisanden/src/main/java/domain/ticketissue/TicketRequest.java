package domain.ticketissue;

import domain.member.MemberNo;
import domain.price.TicketPriceType;
import exception.EmptyValueException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * チケットリクエスト エンティティ
 */
@Getter
@EqualsAndHashCode(of = "requestSeq")
@ToString
public class TicketRequest {
    // 申込連番
    private final int requestSeq;
    // 会員番号
    private MemberNo memberNo;
    // チケットタイプ
    private TicketPriceType ticketPriceType;
    // 3Dメガネあり
    private boolean has3DGlasses = false;

    // チケット
    private Ticket ticket;

    public TicketRequest(int requestSeq, MemberNo memberNo, TicketPriceType ticketPriceType) {
        this(requestSeq, memberNo, ticketPriceType, false);
    }

    public TicketRequest(int requestSeq, MemberNo memberNo, TicketPriceType ticketPriceType, boolean has3DGlasses) {
        this.requestSeq = requestSeq;
        changeMemberNo(memberNo);
        changeTicketType(ticketPriceType);
        changeHas3DGlasses(has3DGlasses);
    }

    public void changeMemberNo(MemberNo memberNo) {
        this.memberNo = memberNo;
    }

    public void changeTicketType(TicketPriceType ticketPriceType) {
        this.ticketPriceType = ticketPriceType;
    }

    public void changeHas3DGlasses(boolean has3DGlasses) {
        this.has3DGlasses = has3DGlasses;
    }

    public void issueTicket(int price) throws EmptyValueException {
        this.ticket = new Ticket(ticketPriceType, new BigDecimal(price));
    }
}
