package domain.screen;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * スクリーン エンティティ
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "screenNo")
public class Screen {
    // スクリーンNo
    private final ScreenNo screenNo;
    // 座席数
    private final int numberOfSeats;
}
