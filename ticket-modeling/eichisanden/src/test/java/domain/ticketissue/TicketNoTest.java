package domain.ticketissue;

import exception.ApplicationException;
import exception.EmptyValueException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TicketNoTest {
    @Test
    public void 自動発番のテスト() {
        var ticketNo1 = new TicketNo();
        assertThat(ticketNo1.getValue().length(), is(36));
        var ticketNo2 = new TicketNo();
        assertThat(ticketNo2.getValue().length(), is(36));

        assertThat(ticketNo1.equals(ticketNo2), is(false));
    }

    @Test
    public void 手動発番のテスト() throws ApplicationException {
        var ticketNo = new TicketNo("1");
        assertThat(ticketNo.getValue(), is("1"));
    }

    @Test(expected = EmptyValueException.class)
    public void 未設定() throws ApplicationException {
        new TicketNo(null);
    }
}