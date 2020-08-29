package domain.ticketissue;

import domain.member.IMemberRepository;
import domain.member.MemberNo;
import domain.movie.IMovieRepository;
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
    // エムアイカードあり
    private final boolean hasMICard;
    // 駐車場パーク80割引
    private final boolean hasPark80;
    // チケット申込
    private final List<TicketRequest> ticketRequests;
    private final IRoadShowRepository roadShowRepository;
    private final IMovieRepository movieRepository;
    private final IMemberRepository memberRepository;
    // 合計金額
    private BigDecimal price;

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
        long c1 = countByTicketIssueType(ticketRequests, TicketRequestType.HANDICAP_HIGH);
        long c2 = countByTicketIssueType(ticketRequests, TicketRequestType.HANDICAP_HIGH_ESCORT);
        long c3 = countByTicketIssueType(ticketRequests, TicketRequestType.HANDICAP_LOW);
        long c4 = countByTicketIssueType(ticketRequests, TicketRequestType.HANDICAP_LOW_ESCORT);
        if (c1 < c2 || c3 < c4) {
            throw new LogicalException("障がい者の同伴者割引は1名までです");
        }

        this.ticketIssueNo = ticketIssueNo;
        this.roadshowId = roadshowId;
        this.hasMICard = hasMICard;
        this.hasPark80 = hasPark80;
        this.price = BigDecimal.ZERO;
        this.ticketRequests = ticketRequests;
        this.roadShowRepository = roadShowRepository;
        this.movieRepository = movieRepository;
        this.memberRepository = memberRepository;
    }

    private long countByTicketIssueType(List<TicketRequest> requests, TicketRequestType type) {
        return requests.stream().filter(e -> e.getTicketRequestType().equals(type)).count();
    }

    public TicketIssue issue() throws ApplicationException {
        // 上映を取得
        var roadShow = this.roadShowRepository.find(this.roadshowId);

        // 映画を取得
        var movie = this.movieRepository.find(roadShow.getMovieId());

        for (TicketRequest request : this.ticketRequests) {
            TicketType ticketType;
            int unitPrice = 0;
            boolean isMember = isMember(request.getMemberNo());

            switch (request.getTicketRequestType()) {
                // 障害者（高校以下）
                case HANDICAP_LOW:
                    unitPrice = 900;
                    ticketType = TicketType.HANDICAP_LOW;
                    break;
                // 障害者（高校以下）同伴者
                case HANDICAP_LOW_ESCORT:
                    unitPrice = 900;
                    ticketType = TicketType.HANDICAP_LOW_ESCORT;
                    break;
                // 障害者（学生以上）
                case HANDICAP_HIGH:
                    unitPrice = 1000;
                    ticketType = TicketType.HANDICAP_HIGH;
                    break;
                // 障害者（学生以上）同伴者
                case HANDICAP_HIGH_ESCORT:
                    unitPrice = 1000;
                    ticketType = TicketType.HANDICAP_HIGH_ESCORT;
                    break;
                // 中・高校生
                case STUDENT_MIDDLE:
                    unitPrice = 1000;
                    ticketType = TicketType.STUDENT_MIDDLE;
                    break;
                // 幼児（3才以上）・小学生
                case STUDENT_LOW:
                    unitPrice = 1000;
                    ticketType = TicketType.STUDENT_LOW;
                    break;
                // シニア（70才以上）
                case SENIOR:
                    if (isMember) {
                        unitPrice = 1000;
                        ticketType = TicketType.CINEMA_CITIZEN_OVER60;
                    } else {
                        unitPrice = 1100;
                        ticketType = TicketType.SENIOR;
                    }
                    break;
                // 学生（大・専）
                case STUDENT_HIGH:
                    ticketType = TicketType.STUDENT_HIGH;
                    if (roadShow.isMovieDay()) {
                        unitPrice = 1100;
                    }
                    if (roadShow.isEarlyShow()) {
                        unitPrice = 1500;
                    } else {
                        unitPrice = 1300;
                    }
                    break;
                // 一般
                case GENERAL:
                    // 一般
                    if (isMember) {
                        ticketType = TicketType.CINEMA_CITIZEN;

                        if (roadShow.isWeekday()) {
                            unitPrice = 1000;
                        }
                        if (roadShow.isHoliday()) {
                            if (roadShow.isEarlyShow()) {
                                if (!roadShow.isMovieDay()) {
                                    unitPrice = 1300;
                                } else {
                                    unitPrice = 1100;
                                }
                            } else {
                                unitPrice = 1000;
                            }
                        }
                    } else {
                        ticketType = TicketType.GENERAL;

                        if (roadShow.isMovieDay()) {
                            unitPrice = 1100;
                        }

                        if (roadShow.isEarlyShow()) {
                            if (this.hasMICard) {
                                ticketType = TicketType.MICARD;
                                unitPrice = 1600;
                            } else {
                                unitPrice = 1800;
                            }
                        }
                        if (roadShow.isLateShow()) {
                            unitPrice = 1300;
                        }
                    }
                    break;
                default:
                    throw new LogicalException("申込種別が存在しません");
            }

            // 駐車場パーク80割引
            if (this.hasPark80) {
                if (roadShow.isEarlyShow() && unitPrice > 1400) {
                    ticketType = TicketType.PARK80;
                    unitPrice = 1400;
                }
                if (roadShow.isLateShow() && unitPrice > 1100) {
                    ticketType = TicketType.PARK80;
                    unitPrice = 1100;
                }
            }

            // 3D作品は一律プラス400円
            if (movie.is3D()) {
                unitPrice += 400;
                // 3Dメガネ（Real D）持参の場合は、100円引き
                if (request.isHas3DGlasses()) {
                    unitPrice -= 100;
                }
            }

            // チケットを発行
            request.issueTicket(ticketType, unitPrice);
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
            System.out.println("CINEMA CITY");
            System.out.println(movie.getTitle() + (movie.is3D() ? "(3D)" : ""));
            System.out.print(dateFormatter.format(roadShow.getScreeningStartTime()) + "〜");
            System.out.println(timeFormatter.format(roadShow.getScreeningEndTime()));
            System.out.print(roadShow.isHoliday() ? "休日割引　" : "");
            System.out.println(roadShow.isLateShow() ? "レイトショー" : "");
            System.out.println("スクリーン" + roadShow.getScreenNo());
            System.out.println("●" + request.getTicket().getTicketType().getTicketTypeName());
            System.out.println(request.getTicket().getCharge() + "円");
            System.out.println(request.isHas3DGlasses() ? "3Dグラスあり" : "");
            System.out.println(request.getMemberNo() == null ? "" : "会員番号 " + request.getMemberNo());
            System.out.println(request.getTicket().getTicketNo());
            System.out.println(request.getRequestSeq() + "/" + ticketRequests.size());
        }
    }
}
