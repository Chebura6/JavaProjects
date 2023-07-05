package is.programmingTechnologies.Accounts;

import is.programmingTechnologies.Exceptions.AccountException;
import is.programmingTechnologies.Interfaces.IAccount;
import is.programmingTechnologies.Models.Config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The debit account class, it encapsulates all account logic.
 *
 * @version 1.0.0 23.02.2023
 */

public class DebitAccount implements IAccount {

    public BigDecimal TransactionRestrictionLimit;
    public BigDecimal Percent;
    public LocalDateTime AccountCreationTime;
    public UUID AccountID;
    public BigDecimal Money;
    BigDecimal CurrentPercentSum;
    BigDecimal TransactionRestrictionUsed;
    BigDecimal CommissionForTransactions;
    public BigDecimal getTransactionRestrictionLimit() {
        return TransactionRestrictionLimit;
    }

    public void setTransactionRestrictionLimit(BigDecimal transactionRestrictionLimit) {
        TransactionRestrictionLimit = transactionRestrictionLimit;
    }

    public BigDecimal getPercent() {
        return Percent;
    }

    public void setPercent(BigDecimal percent) {
        Percent = percent;
    }

    public LocalDateTime getAccountCreationTime() {
        return AccountCreationTime;
    }

    public void setAccountCreationTime(LocalDateTime accountCreationTime) {
        AccountCreationTime = accountCreationTime;
    }

    public UUID getAccountID() {
        return AccountID;
    }

    public void setAccountID(UUID accountID) {
        AccountID = accountID;
    }

    public BigDecimal getMoney() {
        return Money;
    }

    public void setMoney(BigDecimal money) {
        Money = money;
    }

    public BigDecimal getCurrentPercentSum() {
        return CurrentPercentSum;
    }

    public void setCurrentPercentSum(BigDecimal currentPercentSum) {
        CurrentPercentSum = currentPercentSum;
    }

    public BigDecimal getTransactionRestrictionUsed() {
        return TransactionRestrictionUsed;
    }

    public void setTransactionRestrictionUsed(BigDecimal transactionRestrictionUsed) {
        TransactionRestrictionUsed = transactionRestrictionUsed;
    }

    public BigDecimal getCommissionForTransactions() {
        return CommissionForTransactions;
    }

    public void setCommissionForTransactions(BigDecimal commissionForTransactions) {
        CommissionForTransactions = commissionForTransactions;
    }

    /**
     * Account constructor
     */

    public DebitAccount() {
        Percent = new BigDecimal(0);
        Money = new BigDecimal(0);
        CurrentPercentSum = new BigDecimal(0);
        AccountID = UUID.randomUUID();

    }

    /**
     * Calculate percentage sum every day
     */

    @Override
    public void calculatePercentages() {
        CurrentPercentSum = CurrentPercentSum.add(getMoney().multiply(getPercent()));
    }

    /**
     * Charge the amount accumulated for the month
     */

    @Override
    public void chargeInterests() {
        Money = Money.add(CurrentPercentSum);
        CurrentPercentSum = new BigDecimal(0);
    }

    /**
     * Initialize necessary fields using config data
     *
     * @param config stores data for fields initializing
     */

    @Override
    public void fillWithData(Config config) {
        Percent = config.getDebitPercent().divide(new BigDecimal(365), 2, RoundingMode.HALF_UP);
        Money = new BigDecimal(0);
        CurrentPercentSum = new BigDecimal(0);
        CommissionForTransactions = config.CommissionForTransactions;
    }

    /**
     * Encapsulate logic for withdrawing money from the account balance.
     *
     * @param amount amount to withdraw
     * @throws AccountException
     */

    @Override
    public void withdrawMoney(BigDecimal amount) throws AccountException {
        if (TransactionRestrictionLimit == new BigDecimal(-1))
        {
            TransactionRestrictionUsed = new BigDecimal(0);
        }
        else
        {
            if (TransactionRestrictionUsed.add(amount).compareTo(TransactionRestrictionLimit) > 0) throw AccountException.LimitExceeded();
            setTransactionRestrictionUsed(getTransactionRestrictionUsed().add(amount));
        }

        if (getMoney().compareTo(amount) < 0) throw AccountException.NotEnoughMoney();
        setMoney(getMoney().subtract(amount));

    }

    /**
     * Encapsulate logic for topuping money on the account balance.
     *
     * @param amount amount to topup
     * @throws AccountException
     */

    @Override
    public void topupMoney(BigDecimal amount) throws AccountException {
        if (getTransactionRestrictionLimit().compareTo(new BigDecimal(-1)) == 0)
        {
            setTransactionRestrictionUsed(new BigDecimal(0));
        }
        else
        {
            if (TransactionRestrictionUsed.add(amount).compareTo(TransactionRestrictionLimit) > 0) throw AccountException.LimitExceeded();
            setTransactionRestrictionUsed(getTransactionRestrictionUsed().add(amount));
        }

        setMoney(getMoney().add(amount));

    }

    /**
     * Nothing to do in this account type
     *
     * @param amount
     */

    @Override
    public void topupDepositInitialAmount(BigDecimal amount) {
        setMoney(getMoney().add(new BigDecimal(0)));
    }

    /**
     * Nothing to do in this account type
     *
     * @param amount
     */

    @Override
    public void revertTopupDepositInitialAmount(BigDecimal amount) {
        setMoney(getMoney().subtract(new BigDecimal(0)));
    }

    /**
     * Remove commision from the account balance
     *
     * @param amount amount to remove
     */

    @Override
    public void removeCommission(BigDecimal amount) throws AccountException{
        if (getMoney().compareTo(amount) < 0) throw AccountException.NotEnoughMoney();
        setMoney(getMoney().subtract(amount.multiply(getCommissionForTransactions())));
    }

    /**
     * Revert commission removing
     *
     * @param amount amount to revert
     */

    @Override
    public void returnCommission(BigDecimal amount) {
        setMoney(getMoney().add(amount.multiply(getCommissionForTransactions())));
    }

    /**
     * Nothing to do in this account type
     *
     * @param dateTime
     * @return boolean is setting time was done
     */

    @Override
    public boolean setTime(LocalDateTime dateTime) {
        return false;
    }

    /**
     * toString override
     *
     * @return String
     */

    public String toString()
    {
        return "\n" + "Account type:" + "Debit account\n" + "Money: " + Money + "\nTransaction restriction limit: " + TransactionRestrictionLimit + "\nUsed transaction restriction: " + TransactionRestrictionUsed + "\nAccount ID: " + AccountID;
    }

}
