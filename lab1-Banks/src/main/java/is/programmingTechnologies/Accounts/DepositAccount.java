package is.programmingTechnologies.Accounts;

import is.programmingTechnologies.Exceptions.AccountException;
import is.programmingTechnologies.Interfaces.IAccount;
import is.programmingTechnologies.Models.Config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The credit account class, it encapsulates all account logic.
 *
 * @version 1.0.0 23.02.2023
 */

public class DepositAccount implements IAccount {
    BigDecimal TransactionRestrictionLimit;
    LocalDateTime AccountCreationTime;
    UUID AccountID = null;
    BigDecimal Money = null;
    public BigDecimal Percent;
    public LocalDateTime CurrentTime;
    LocalDateTime RestrictionsDuration;
    BigDecimal CurrentPercentSum;
    BigDecimal InitialAmount;
    BigDecimal TransactionRestrictionUsed;
    BigDecimal CommissionForTransactions;

    public BigDecimal getMoney() {
        return Money;
    }

    public void setMoney(BigDecimal money) {
        Money = money;
    }

    public BigDecimal getPercent() {
        return Percent;
    }

    public void setPercent(BigDecimal percent) {
        Percent = percent;
    }

    public LocalDateTime getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        CurrentTime = currentTime;
    }

    public LocalDateTime getRestrictionsDuration() {
        return RestrictionsDuration;
    }

    public void setRestrictionsDuration(LocalDateTime restrictionsDuration) {
        RestrictionsDuration = restrictionsDuration;
    }

    public BigDecimal getCurrentPercentSum() {
        return CurrentPercentSum;
    }

    public void setCurrentPercentSum(BigDecimal currentPercentSum) {
        CurrentPercentSum = currentPercentSum;
    }

    public BigDecimal getInitialAmount() {
        return InitialAmount;
    }

    public void setInitialAmount(BigDecimal initialAmount) {
        InitialAmount = initialAmount;
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

    public DepositAccount() {
        setPercent(new BigDecimal(0));
        setMoney(new BigDecimal(0));
        setCurrentPercentSum(new BigDecimal(0));
        setInitialAmount(new BigDecimal(0));
        setAccountID(UUID.randomUUID());

    }

    @Override
    public BigDecimal getTransactionRestrictionLimit() {
        return TransactionRestrictionLimit;
    }

    @Override
    public void setTransactionRestrictionLimit(BigDecimal transactionRestrictionLimit) {
        TransactionRestrictionLimit = transactionRestrictionLimit;
    }

    @Override
    public LocalDateTime getAccountCreationTime() {
        return AccountCreationTime;
    }

    @Override
    public void setAccountCreationTime(LocalDateTime accountCreationTime) {
        if (accountCreationTime == null) throw new NullPointerException();
    }

    @Override
    public UUID getAccountID() {
        return AccountID;
    }

    @Override
    public void setAccountID(UUID accountID) {
        AccountID = accountID;
    }

    /**
     * Calculate percentages
     *
     */
    @Override
    public void calculatePercentages() {
        setCurrentPercentSum(getCurrentPercentSum().add(getMoney().multiply(getPercent())));
    }

    /**
     * Accrue accumulated amount on the balance
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
        setPercent(config.DebitPercent.divide(new BigDecimal(365), 2, RoundingMode.HALF_UP));
        setMoney(new BigDecimal(0));
        setCurrentPercentSum(new BigDecimal(0));
        setInitialAmount(new BigDecimal(0));
        setTransactionRestrictionLimit(config.getTransactionRestrictionLimit());

        setRestrictionsDuration(LocalDateTime.now().plusYears(config.getRestrictionDuration().getYears())
                .plusMonths(config.getRestrictionDuration().getMonths())
                .plusDays(config.getRestrictionDuration().getDays()));
    }

    /**
     * Encapsulate logic for withdrawing money from the account balance
     *
     * @param amount
     * @throws AccountException
     */

    @Override
    public void withdrawMoney(BigDecimal amount) throws AccountException {
        if (getCurrentTime().isAfter(getRestrictionsDuration())) throw AccountException.OperationNotPermitted();

        if (getTransactionRestrictionLimit().compareTo(new BigDecimal(-1)) == 0) {
            setTransactionRestrictionUsed(new BigDecimal(0));
        }
        else {
            if (TransactionRestrictionUsed.add(amount).compareTo(TransactionRestrictionLimit) > 0) throw AccountException.LimitExceeded();
            setTransactionRestrictionUsed(getTransactionRestrictionUsed().add(amount));
        }

        if (getMoney().compareTo(amount) < 0) throw AccountException.NotEnoughMoney();
        setMoney(getMoney().subtract(amount));

    }

    /**
     * Encapsulate logic for topuping money on the account balance
     *
     * @param amount
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
     * Set amount to calculate percents in the future
     *
     * @param amount
     */

    @Override
    public void topupDepositInitialAmount(BigDecimal amount) {
        if (TransactionRestrictionLimit.compareTo(new BigDecimal(-1)) == 0)
        {
            setTransactionRestrictionUsed(new BigDecimal(0));
        }

        setInitialAmount(getInitialAmount().add(amount));
    }

    /**
     * Revert setting amount to calculate percents in the future
     *
     * @param amount
     */

    @Override
    public void revertTopupDepositInitialAmount(BigDecimal amount) {
        if (TransactionRestrictionLimit.compareTo(new BigDecimal(-1)) == 0)
        {
            setTransactionRestrictionUsed(new BigDecimal(0));
        }

        setInitialAmount(getInitialAmount().subtract(amount));
    }

    /**
     * Remove commision from the account balance
     *
     * @param amount
     * @throws AccountException
     */

    @Override
    public void removeCommission(BigDecimal amount) throws AccountException {
        if (getMoney().compareTo(amount) < 0) throw AccountException.NotEnoughMoney();

        setMoney(getMoney().subtract(amount.multiply(getCommissionForTransactions())));
    }

    /**
     * Revert commission removing
     *
     * @param amount
     */

    @Override
    public void returnCommission(BigDecimal amount) {
        setMoney(getMoney().add(amount.multiply(getCommissionForTransactions())));
    }

    /**
     * Set current time in account
     *
     * @param dateTime
     * @return
     */

    @Override
    public boolean setTime(LocalDateTime dateTime) {
        setCurrentTime(dateTime);
        return true;
    }

    /**
     * toString override
     *
     * @return String
     */

    public String toString() {
        return "\n" + "Account type:" + "Deposit account\n" + "Money: " + Money + "\nTransaction restriction limit: " + TransactionRestrictionLimit + "\nUsed transaction restriction: " + TransactionRestrictionUsed +
                "\nAccount initial amount: " + InitialAmount + "\nAccount ID: " + AccountID;
    }
}
