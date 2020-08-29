package domain.ticketissue;

import domain.member.MemberNo;
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
    // 申込種別
    private TicketRequestType ticketRequestType;
    // 3Dメガネあり
    private boolean has3DGlasses = false;

    // チケット
    private Ticket ticket;

    public TicketRequest(int requestSeq, MemberNo memberNo, TicketRequestType ticketRequestType) {
        this(requestSeq, memberNo, ticketRequestType, false);
    }

    public TicketRequest(int requestSeq, MemberNo memberNo, TicketRequestType ticketRequestType, boolean has3DGlasses) {
        this.requestSeq = requestSeq;
        changeMemberNo(memberNo);
        changeTicketRequestType(ticketRequestType);
        changeHas3DGlasses(has3DGlasses);
    }

    public void changeMemberNo(MemberNo memberNo) {
        this.memberNo = memberNo;
    }

    public void changeTicketRequestType(TicketRequestType ticketRequestType) {
        this.ticketRequestType = ticketRequestType;
    }

    public void changeHas3DGlasses(boolean has3DGlasses) {
        this.has3DGlasses = has3DGlasses;
    }

    public void issueTicket(TicketType ticketType, int price) throws EmptyValueException {
        this.ticket = new Ticket(ticketType, new BigDecimal(price));
    }
}
