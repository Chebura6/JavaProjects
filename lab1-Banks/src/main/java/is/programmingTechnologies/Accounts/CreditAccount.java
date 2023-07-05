package is.programmingTechnologies.Accounts;

import is.programmingTechnologies.Exceptions.AccountException;
import is.programmingTechnologies.Interfaces.IAccount;
import is.programmingTechnologies.Models.Config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The credit account class, it encapsulates all account logic.
 *
 * @version 1.0.0 23.02.2023
 */

public class CreditAccount implements IAccount {

    BigDecimal TransactionRestrictionLimit = null;
    BigDecimal Commission;
    BigDecimal CommissionForTransactions;
    BigDecimal CurrentPercentSum;
    BigDecimal TransactionRestrictionUsed;
    LocalDateTime AccountCreationTime = null;
    UUID AccountID = null;
    BigDecimal Money = null;

    public BigDecimal getCommission() {
        return Commission;
    }

    public void setCommission(BigDecimal commission) {
        Commission = commission;
    }

    public BigDecimal getCommissionForTransactions() {
        return CommissionForTransactions;
    }

    public void setCommissionForTransactions(BigDecimal commissionForTransactions) {
        CommissionForTransactions = commissionForTransactions;
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

    public BigDecimal getMoney() {
        return Money;
    }

    public void setMoney(BigDecimal money) {
        Money = money;
    }
    public BigDecimal getTransactionRestrictionLimit() {
        return TransactionRestrictionLimit;
    }

    public void setTransactionRestrictionLimit(BigDecimal transactionRestrictionLimit) {
        TransactionRestrictionLimit = transactionRestrictionLimit;
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
    public CreditAccount() {
        AccountID = UUID.randomUUID();
    }

    /**
     * Calculate percents if the balance is negative.
     *
     * @version 1.0.0 23.02.2023
     */
    @Override
    public void calculatePercentages() {
        if (getMoney().compareTo(new BigDecimal(0)) < 0) setCurrentPercentSum(getCurrentPercentSum().add(getCommission()));
    }

    /**
     * Removes accumulated interest from the account balance.
     *
     * @version 1.0.0 23.02.2023
     */

    @Override
    public void chargeInterests() {
        setMoney(getMoney().subtract(getCurrentPercentSum()));
        setCurrentPercentSum(new BigDecimal(0));
    }

    /**
     * Initialize necessary fields using config data.
     *
     * @param config stores data for fields initializing
     */

    @Override
    public void fillWithData(Config config) {
        setCommission(config.getCreditCommission());
        setMoney(new BigDecimal(0));
        setCurrentPercentSum(new BigDecimal(0));
        setCommissionForTransactions(config.getCommissionForTransactions());
    }

    /**
     * Encapsulate logic for withdrawing money from the account balance.
     *
     * @param amount amount to withdraw
     * @throws AccountException
     */
    @Override
    public void withdrawMoney(BigDecimal amount) throws AccountException {
        if (TransactionRestrictionLimit.compareTo(new BigDecimal(-1)) == 0) {
            setTransactionRestrictionUsed(new BigDecimal(0));
        }
        else {
            if (TransactionRestrictionUsed.add(amount).compareTo(TransactionRestrictionLimit) > 0) throw AccountException.LimitExceeded();
            setTransactionRestrictionUsed(getTransactionRestrictionUsed().add(amount));
        }

        setMoney(getMoney().subtract(amount));
    }

    /**
     * Encapsulate logic for topuping money on the account balance.
     *
     * @param amount amount to topup
     * @throws AccountException
     */
    @Override
    public void topupMoney(BigDecimal amount) throws AccountException{
        if (TransactionRestrictionLimit.compareTo(new BigDecimal(-1)) == 0) {
            setTransactionRestrictionUsed(new BigDecimal(0));
        }
        else {
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
    public void removeCommission(BigDecimal amount) {
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


    public String toString() {
        return "\n" + "Account type: " +"Credit account\n" +
                "Money: " + Money + "\nTransaction restriction limit: " +
                TransactionRestrictionLimit + "\nUsed transaction restriction: " +
                TransactionRestrictionUsed + "\nAccount ID: " + AccountID;
    }
}
