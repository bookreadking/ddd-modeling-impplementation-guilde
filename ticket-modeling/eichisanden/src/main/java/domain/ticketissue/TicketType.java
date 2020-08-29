package domain.ticketissue;

import lombok.Getter;

/**
 * チケットタイプ
 */
@Getter
public enum TicketType {
    CINEMA_CITIZEN("シネマシティズン"),
    CINEMA_CITIZEN_OVER60("シネマシティズン（60才以上）"),
    GENERAL("一般"),
    SENIOR("シニア（70才以上）"),
    STUDENT_HIGH("学生（大・専）"),
    STUDENT_MIDDLE("中・高校生"),
    STUDENT_LOW("幼児（3才以上）・小学生"),
    HANDICAP_HIGH("障がい者（学生以上）"),
    HANDICAP_HIGH_ESCORT("障がい者（学生以上）同伴者"),
    HANDICAP_LOW("障がい者（高校以下）"),
    HANDICAP_LOW_ESCORT("障がい者（高校以下）同伴者"),
    MICARD("エムアイカード"),
    PARK80("駐車場パーク80割引");

    private final String ticketTypeName;

    TicketType(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }
}
