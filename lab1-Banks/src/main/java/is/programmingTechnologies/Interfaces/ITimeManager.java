package is.programmingTechnologies.Interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Time manager interface.
 */

public interface ITimeManager {
    LocalDateTime CurrentTime = null;
    LocalDateTime getCurrentTime();
    LocalDateTime increaseTimeFor1Hour();
    void increaseTimeFor1Day();
    void increaseTimeFor1Month();
    LocalDateTime decreaseTimeFor1Hour();
}
