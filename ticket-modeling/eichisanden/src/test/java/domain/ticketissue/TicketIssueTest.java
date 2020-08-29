package domain.ticketissue;

import domain.member.MemberNo;
import domain.roadshow.RoadShowId;
import exception.ApplicationException;
import infrastructure.member.MemberRepository;
import infrastructure.movie.MovieRepository;
import infrastructure.roadshow.RoadShowRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TicketIssueTest {

    @Test
    public void シネマシチズン() throws ApplicationException {
        List<TicketRequest> ticketRequests = new ArrayList<>();
        ticketRequests.add(new TicketRequest(1, new MemberNo("1"), TicketRequestType.GENERAL));
        var ticketIssue = new TicketIssue(new TicketIssueNo(),
                new RoadShowId("2"),
                false,
                false,
                ticketRequests,
                new RoadShowRepository(),
                new MovieRepository(),
                new MemberRepository()
        );
        ticketIssue.issue();
    }

    @Test
    public void シネマシティズン60才以上() throws ApplicationException {
        List<TicketRequest> ticketRequests = new ArrayList<>();
        ticketRequests.add(new TicketRequest(1, new MemberNo("2"), TicketRequestType.SENIOR));
        var ticketIssue = new TicketIssue(new TicketIssueNo(),
                new RoadShowId("4"),
                false,
                false,
                ticketRequests,
                new RoadShowRepository(),
                new MovieRepository(),
                new MemberRepository()
        );
        ticketIssue.issue();
    }

    @Test
    public void 中高校生() throws ApplicationException {
        List<TicketRequest> ticketRequests = new ArrayList<>();
        ticketRequests.add(new TicketRequest(1, new MemberNo("1"), TicketRequestType.STUDENT_MIDDLE));
        var ticketIssue = new TicketIssue(new TicketIssueNo(),
                new RoadShowId("4"),
                false,
                false,
                ticketRequests,
                new RoadShowRepository(),
                new MovieRepository(),
                new MemberRepository()
        );
        ticketIssue.issue();
    }

    @Test
    public void エムアイカード() throws ApplicationException {
        List<TicketRequest> ticketRequests = new ArrayList<>();
        ticketRequests.add(new TicketRequest(1, null, TicketRequestType.GENERAL));
        var ticketIssue = new TicketIssue(new TicketIssueNo(),
                new RoadShowId("4"),
                true,
                false,
                ticketRequests,
                new RoadShowRepository(),
                new MovieRepository(),
                new MemberRepository()
        );
        ticketIssue.issue();
    }

    @Test
    public void 駐車場80割引() throws ApplicationException {
        List<TicketRequest> ticketRequests = new ArrayList<>();
        ticketRequests.add(new TicketRequest(1, null, TicketRequestType.GENERAL));
        var ticketIssue = new TicketIssue(new TicketIssueNo(),
                new RoadShowId("4"),
                false,
                true,
                ticketRequests,
                new RoadShowRepository(),
                new MovieRepository(),
                new MemberRepository()
        );
        ticketIssue.issue();
    }

    @Test
    public void 障害者高校以下同伴あり() throws ApplicationException {
        List<TicketRequest> ticketRequests = new ArrayList<>();
        ticketRequests.add(new TicketRequest(1, null, TicketRequestType.HANDICAP_LOW_ESCORT));
        ticketRequests.add(new TicketRequest(2, null, TicketRequestType.HANDICAP_LOW));
        var ticketIssue = new TicketIssue(new TicketIssueNo(),
                new RoadShowId("4"),
                false,
                false,
                ticketRequests,
                new RoadShowRepository(),
                new MovieRepository(),
                new MemberRepository()
        );
        ticketIssue.issue();
    }

    @Test
    public void 複数枚発行() throws ApplicationException {
        List<TicketRequest> ticketRequests = new ArrayList<>();
        ticketRequests.add(new TicketRequest(1, new MemberNo("1"), TicketRequestType.GENERAL));
        ticketRequests.add(new TicketRequest(2, new MemberNo("2"), TicketRequestType.SENIOR));
        ticketRequests.add(new TicketRequest(3, null, TicketRequestType.GENERAL));
        ticketRequests.add(new TicketRequest(4, null, TicketRequestType.SENIOR));
        ticketRequests.add(new TicketRequest(5, null, TicketRequestType.STUDENT_HIGH));
        ticketRequests.add(new TicketRequest(6, null, TicketRequestType.STUDENT_MIDDLE));
        ticketRequests.add(new TicketRequest(7, null, TicketRequestType.STUDENT_LOW));
        ticketRequests.add(new TicketRequest(8, null, TicketRequestType.HANDICAP_HIGH));
        ticketRequests.add(new TicketRequest(9, null, TicketRequestType.HANDICAP_HIGH_ESCORT));
        ticketRequests.add(new TicketRequest(10, null, TicketRequestType.HANDICAP_LOW));
        ticketRequests.add(new TicketRequest(11, null, TicketRequestType.HANDICAP_LOW_ESCORT));
        var ticketIssue = new TicketIssue(new TicketIssueNo(),
                new RoadShowId("4"),
                false,
                false,
                ticketRequests,
                new RoadShowRepository(),
                new MovieRepository(),
                new MemberRepository()
        );
        ticketIssue.issue();
    }

    @Test
    public void 一般3D() throws ApplicationException {
        List<TicketRequest> ticketRequests = new ArrayList<>();
        ticketRequests.add(new TicketRequest(1, null, TicketRequestType.GENERAL));
        var ticketIssue = new TicketIssue(new TicketIssueNo(),
                new RoadShowId("1"),
                false,
                false,
                ticketRequests,
                new RoadShowRepository(),
                new MovieRepository(),
                new MemberRepository()
        );
        ticketIssue.issue();
    }

}