package is.programmingTechnologies.Interfaces;

import is.programmingTechnologies.Exceptions.AccountException;
import is.programmingTechnologies.Models.Config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Account interface.
 */

public interface IAccount {
    void setTransactionRestrictionUsed(BigDecimal used);
    public BigDecimal getTransactionRestrictionLimit();
    public void setTransactionRestrictionLimit(BigDecimal transactionRestrictionLimit);
    public LocalDateTime getAccountCreationTime();
    public void setAccountCreationTime(LocalDateTime accountCreationTime);
    public UUID getAccountID();
    public void setAccountID(UUID accountID);
    void calculatePercentages();
    void chargeInterests();
    void fillWithData(Config config);
    void withdrawMoney(BigDecimal amount) throws AccountException;
    void topupMoney(BigDecimal amount) throws AccountException;
    void topupDepositInitialAmount(BigDecimal amount);
    void revertTopupDepositInitialAmount(BigDecimal amount);
    void removeCommission(BigDecimal amount) throws AccountException;
    void returnCommission(BigDecimal amount);
    boolean setTime(LocalDateTime dateTime);

}
