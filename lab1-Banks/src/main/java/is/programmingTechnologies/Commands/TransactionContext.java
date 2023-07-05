package is.programmingTechnologies.Commands;

import is.programmingTechnologies.Interfaces.IAccount;

import java.math.BigDecimal;

/**
 * This class store information about transaction.
 */

public class TransactionContext {

    /**
     * Transaction context constructor
     *
     * @param from which account to withdraw money from
     * @param to which account to withdraw money еto
     * @param amount amount to transfer
     */

    public TransactionContext(IAccount from, IAccount to, BigDecimal amount)
    {
        From = from;
        To = to;
        Amount = amount;
    }

    public IAccount From;
    public IAccount To;
    public BigDecimal Amount;

    public IAccount getFrom() {
        return From;
    }

    public void setFrom(IAccount from) {
        From = from;
    }

    public IAccount getTo() {
        return To;
    }

    public void setTo(IAccount to) {
        To = to;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }
}
