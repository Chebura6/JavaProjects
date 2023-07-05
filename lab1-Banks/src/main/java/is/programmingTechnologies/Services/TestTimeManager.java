package is.programmingTechnologies.Services;

import is.programmingTechnologies.Interfaces.ICentralBank;
import is.programmingTechnologies.Interfaces.ITimeManager;

import java.time.LocalDateTime;

/**
 * This class let change time for testing system.
 *
 * @version 1.0.0 23.02.2023
 */

public class TestTimeManager implements ITimeManager {
    private final int _hoursPerDay = 24;

    private ICentralBank _centralBank;
    private LocalDateTime CurrentTime;

    /**
     * Test time manager constructor
     * @param centralBank
     */

    public TestTimeManager (ICentralBank centralBank) {
        if (centralBank == null) throw new NullPointerException("Null central bank!");
        _centralBank = centralBank;
        CurrentTime = LocalDateTime.now();
    }

    /**
     * Return current time
     * @return LocalDateTime
     */

    @Override
    public LocalDateTime getCurrentTime() {
        return CurrentTime;
    }

    /**
     * Increase time for 1 hour
     * @return LocalDateTime
     */

    @Override
    public LocalDateTime increaseTimeFor1Hour() {
        int prevDay = CurrentTime.getDayOfMonth();
        if (CurrentTime.getHour() == 23)
        {
            _centralBank.calculatePercentages();
        }

        CurrentTime = CurrentTime.plusHours(1);
        _centralBank.changeAccountsTime(CurrentTime);
        if (CurrentTime.getDayOfMonth() == 1 && prevDay > 27)
        {
            _centralBank.chargeInterest();
        }

        prevDay = CurrentTime.getDayOfMonth();
        return CurrentTime;
    }

    /**
     * Use IncreaseTimeFor1Hour() in cycle
     */

    @Override
    public void increaseTimeFor1Day() {
        for (int i = 0; i < _hoursPerDay; ++i)
        {
            increaseTimeFor1Hour();
        }

    }

    /**
     * Use IncreaseTimeFor1Day() in cycle
     */

    @Override
    public void increaseTimeFor1Month() {
        int day = CurrentTime.getDayOfMonth();
        increaseTimeFor1Day();
        while (CurrentTime.getDayOfMonth() != day)
        {
            increaseTimeFor1Day();
        }
    }

    /**
     * Decrease time fo 1 hour
     * @return
     */

    @Override
    public LocalDateTime decreaseTimeFor1Hour() {
        CurrentTime = CurrentTime.minusHours(1);
        _centralBank.timeHasBeenDecreased(CurrentTime);
        return CurrentTime;

    }
}
