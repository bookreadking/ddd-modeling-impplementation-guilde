package domain.ticketissue;

/**
 * チケットリクエストタイプ
 */
public enum TicketRequestType {
    // 一般
    GENERAL,
    // シニア
    SENIOR,
    // 学生（大・専）
    STUDENT_HIGH,
    // 中・高生
    STUDENT_MIDDLE,
    // 幼児（3才以上）・小学生
    STUDENT_LOW,
    // 障がい者（学生以上）
    HANDICAP_HIGH,
    // 障がい者（学生以上）同伴者
    HANDICAP_HIGH_ESCORT,
    // 障がい者（高校以下）
    HANDICAP_LOW,
    // 障がい者（高校以下）同伴者
    HANDICAP_LOW_ESCORT
}
