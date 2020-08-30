package domain.ticketissue;

import domain.member.IMemberRepository;
import domain.member.MemberNo;
import domain.movie.IMovieRepository;
import domain.price.TicketPriceType;
import domain.roadshow.IRoadShowRepository;
import domain.roadshow.RoadShowId;
import exception.ApplicationException;
import exception.EmptyValueException;
import exception.LogicalException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * チケット発券 エンティティ
 */
@Getter
@EqualsAndHashCode(of = "ticketIssueNo")
@ToString
public class TicketIssue {
    // 発券No
    private final TicketIssueNo ticketIssueNo;
    // 上映ID
    private final RoadShowId roadshowId;
    // チケット申込
    private final List<TicketRequest> ticketRequests;
    // 合計金額
    private BigDecimal price;

    private final IRoadShowRepository roadShowRepository;
    private final IMovieRepository movieRepository;
    private final IMemberRepository memberRepository;

    public TicketIssue(TicketIssueNo ticketIssueNo,
                       RoadShowId roadshowId,
                       boolean hasMICard,
                       boolean hasPark80,
                       List<TicketRequest> ticketRequests,
                       IRoadShowRepository roadShowRepository,
                       IMovieRepository movieRepository,
                       IMemberRepository memberRepository) throws LogicalException, EmptyValueException {

        if (ticketIssueNo == null) {
            throw new EmptyValueException("発券Noは必須です");
        }
        if (roadshowId == null) {
            throw new EmptyValueException("上映IDは必須です");
        }
        if (ticketRequests == null || ticketRequests.isEmpty()) {
            throw new EmptyValueException("チケット申込は必須です");
        }
        long c1 = countByTicketType(ticketRequests, TicketPriceType.HANDICAP_HIGH);
        long c2 = countByTicketType(ticketRequests, TicketPriceType.HANDICAP_HIGH_ESCORT);
        long c3 = countByTicketType(ticketRequests, TicketPriceType.HANDICAP_LOW);
        long c4 = countByTicketType(ticketRequests, TicketPriceType.HANDICAP_LOW_ESCORT);
        if (c1 < c2 || c3 < c4) {
            throw new LogicalException("障がい者の同伴者割引は1名までです");
        }

        this.ticketIssueNo = ticketIssueNo;
        this.roadshowId = roadshowId;
        this.price = BigDecimal.ZERO;
        this.ticketRequests = ticketRequests;
        this.roadShowRepository = roadShowRepository;
        this.movieRepository = movieRepository;
        this.memberRepository = memberRepository;
    }

    private long countByTicketType(List<TicketRequest> requests, TicketPriceType type) {
        return requests.stream().filter(e -> e.getTicketPriceType().equals(type)).count();
    }

    public TicketIssue issue() throws ApplicationException {
        // 上映を取得
        var roadShow = this.roadShowRepository.find(this.roadshowId);
        // 映画を取得
        var movie = this.movieRepository.find(roadShow.getMovieId());

        var isWeekday = roadShow.isWeekday();
        var isEarlyShow = roadShow.isEarlyShow();
        var isLateShow = roadShow.isLateShow();
        var isMovieDay = roadShow.isMovieDay();

        for (TicketRequest request : this.ticketRequests) {
            boolean isMember = isMember(request.getMemberNo());

            // チケットの単価を取得
            var unitPrice = request.getTicketPriceType().getPrice(isWeekday, isLateShow, isMovieDay);

            // チケットを発行
            request.issueTicket(unitPrice);
            this.price = this.price.add(new BigDecimal(unitPrice));
        }
        printTicket();
        return this;
    }

    public boolean isMember(MemberNo memberNo) throws ApplicationException {
        if (memberNo == null) {
            return false;
        }
        var member = this.memberRepository.find(memberNo);

        if (member == null) {
            throw new LogicalException("会員情報が登録されていません: " + memberNo);
        }
        return true;
    }

    public void printTicket() {
        // 上映を取得
        var roadShow = this.roadShowRepository.find(this.roadshowId);
        // 映画を取得
        var movie = this.movieRepository.find(roadShow.getMovieId());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd(E) HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("チケット発行番号 " + ticketIssueNo);
        System.out.println("発券枚数 " + ticketRequests.size());
        System.out.println("合計金額 " + price);
        for (TicketRequest request : ticketRequests) {
            System.out.println("--------------------------------------------------");
            System.out.println(movie.getTitle() + (movie.is3D() ? "(3D)" : ""));
            System.out.print(dateFormatter.format(roadShow.getScreeningStartTime()) + "〜");
            System.out.println(timeFormatter.format(roadShow.getScreeningEndTime()));
            System.out.print(roadShow.isHoliday() ? "休日割引　" : "");
            System.out.println(roadShow.isLateShow() ? "レイトショー" : "");
            System.out.println("スクリーン" + roadShow.getScreenNo());
            System.out.println("●" + request.getTicketPriceType().getTicketPriceName());
            System.out.println(request.getTicket().getCharge() + "円");
            System.out.println(request.isHas3DGlasses() ? "3Dグラスあり" : "");
            System.out.println(request.getMemberNo() == null ? "" : "会員番号 " + request.getMemberNo());
            System.out.println(request.getTicket().getTicketNo());
            System.out.println(request.getRequestSeq() + "/" + ticketRequests.size());
        }
    }
}
