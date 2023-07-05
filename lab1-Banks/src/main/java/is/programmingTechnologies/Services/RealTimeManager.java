package is.programmingTechnologies.Services;

import is.programmingTechnologies.Interfaces.ITimeManager;

import java.time.LocalDateTime;

/**
 * This class is only for getting current time.
 *
 * @version 1.0.0 23.02.2023
 */

public class RealTimeManager implements ITimeManager {
    LocalDateTime CurrentTime;
    public LocalDateTime getCurrentTime() {
        return CurrentTime;
    }

    /**
     * Return current time
     * @return
     */
    @Override
    public LocalDateTime increaseTimeFor1Hour() {
        return CurrentTime;
    }

    /**
     * Use IncreaseTimeFor1Hour() in cycle
     */

    @Override
    public void increaseTimeFor1Day() {
        for (int i = 0; i < 24; ++i) {
            increaseTimeFor1Hour();
        }
    }

    /**
     * Use IncreaseTimeFor1Day() in cycle
     */

    @Override
    public void increaseTimeFor1Month() {
        for (int i = 0; i < 30; ++i) {
            increaseTimeFor1Day();
        }

    }

    /**
     * Just return current time
     * @return LocalDateTime
     */

    @Override
    public LocalDateTime decreaseTimeFor1Hour() {
        return CurrentTime;
    }
}
