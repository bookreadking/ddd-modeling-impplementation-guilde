package domain.price;

/**
 * チケットタイプ
 */
public enum TicketPriceType {
    CINEMA_CITIZEN("シネマシティズン", 1000, 1000, 1300, 1000, 1100),
    CINEMA_CITIZEN_OVER60("シネマシティズン（60才以上）", 1000, 1000, 1000, 1000, 1000),
    GENERAL("一般", 1800, 1300, 1800, 1300, 1100),
    SENIOR("シニア（70才以上）", 1100, 1100, 1100, 1100, 1100),
    STUDENT_HIGH("学生（大・専）", 1500, 1300, 1500, 1300, 1100),
    STUDENT_MIDDLE("中・高校生", 1000, 1000, 1000, 1000, 1000),
    STUDENT_LOW("幼児（3才以上）・小学生", 1000, 1000, 1000, 1000, 1000),
    HANDICAP_HIGH("障がい者（学生以上）", 1000, 1000, 1000, 1000, 1000),
    HANDICAP_HIGH_ESCORT("障がい者（学生以上）同伴者", 1000, 1000, 1000, 1000, 1000),
    HANDICAP_LOW("障がい者（高校以下）", 900, 900, 900, 900, 900),
    HANDICAP_LOW_ESCORT("障がい者（高校以下）同伴者", 900, 900, 900, 900, 900),
    MICARD("エムアイカード", 1600, 1300, 1600, 1300, -1),
    PARK80("駐車場パーク80割引", 1400, 1100, 1400, 1100, -1);

    private final String ticketPriceName;
    private final int weekday;
    private final int weekdayLate;
    private final int holiday;
    private final int holidayLate;
    private final int movieDay;

    TicketPriceType(String ticketPriceName, int weekday, int weekdayLate, int holiday, int holidayLate, int movieDay) {
        this.ticketPriceName = ticketPriceName;
        this.weekday = weekday;
        this.weekdayLate = weekdayLate;
        this.holiday = holiday;
        this.holidayLate = holidayLate;
        this.movieDay = movieDay;
    }

    public String getTicketPriceName() {
        return this.ticketPriceName;
    }

    public int getPrice(boolean isWeekday, boolean isLateShow, boolean isMovieDay) {

        if (isMovieDay) {
            // シネマシチズンは土日の早い時間だけ映画の日の金額の方が安いので設定する
            if (this.equals(CINEMA_CITIZEN) && !isLateShow) {
                return movieDay;
            }
            // エムアイカードと駐車場パーク80割引は映画の日割引なし
            if (!this.equals(MICARD) && !this.equals(PARK80)) {
                return movieDay;
            }
        }

        if (isWeekday) {
            if (isLateShow) {
                return weekdayLate;
            }
            return weekday;
        } else {
            if (isLateShow) {
                return holidayLate;
            }
            return holiday;
        }
    }

}
