package is.programmingTechnologies.Models;

import is.programmingTechnologies.Entities.Bank;
import is.programmingTechnologies.Exceptions.ConfigException;
//import javafx.util.Pair;

import java.math.BigDecimal;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class store bank configuration settings.
 *
 * @version 1.0.0 23.02.2023
 */

public class Config {

    private TreeMap<BigDecimal, BigDecimal> _percentsForDepositAccounts;

    /**
     * Configuration object constructor
     * @param debitPercent interest on the debit account balance
     * @param lowerBounds list of lower bounds
     * @param percents list of percents depends on lower bounds
     * @param creditCommission percent for credit accounts (if balance is negative)
     * @param restrictionDuration duration of operation's restriction
     * @param transactionRestrictionLimit restriction limit
     * @param commissionForTransactions commission for transactions
     * @throws Exception
     */

    public Config(BigDecimal debitPercent, List<BigDecimal> lowerBounds, List<BigDecimal> percents, BigDecimal creditCommission, Period restrictionDuration, BigDecimal transactionRestrictionLimit, BigDecimal commissionForTransactions) throws Exception {
        if (restrictionDuration == null) throw new NullPointerException("Null restriction duration");
        if (lowerBounds == null) throw new NullPointerException("Null lower bounds");
        if (percents == null) throw new NullPointerException("Null percents");
        if (debitPercent.compareTo(new BigDecimal(0)) < 0) throw ConfigException.NegativePercent();
        if (creditCommission.compareTo(new BigDecimal(0)) < 0) throw ConfigException.NegativeCommission();
        if (transactionRestrictionLimit.compareTo(new BigDecimal(0)) < 0) throw ConfigException.NegativeLimit();
        if (commissionForTransactions.compareTo(new BigDecimal(0)) < 0) throw ConfigException.NegativeCommission();
        if (lowerBounds.size() != percents.size()) throw ConfigException.InvalidArgumentsInConstructor();
        _percentsForDepositAccounts = new TreeMap<BigDecimal, BigDecimal>();
        for (int i = 0; i < lowerBounds.size(); ++i) {
            _percentsForDepositAccounts.put(lowerBounds.get(i), percents.get(i));
        }

        DebitPercent = debitPercent;
        CreditCommission = creditCommission;
        RestrictionDuration = restrictionDuration;
        TransactionRestrictionLimit = transactionRestrictionLimit;
        CommissionForTransactions = commissionForTransactions;
    }

    public Bank Bank;
    public BigDecimal DebitPercent;
    public BigDecimal CreditCommission;
    public Period RestrictionDuration;
    public BigDecimal TransactionRestrictionLimit;
    public BigDecimal CommissionForTransactions;

    public BigDecimal getDebitPercent() {
        return DebitPercent;
    }

    public void setDebitPercent(BigDecimal debitPercent) {
        DebitPercent = debitPercent;
    }

    public BigDecimal getCreditCommission() {
        return CreditCommission;
    }

    public void setCreditCommission(BigDecimal creditCommission) {
        CreditCommission = creditCommission;
    }

    public Period getRestrictionDuration() {
        return RestrictionDuration;
    }

    public void setRestrictionDuration(Period restrictionDuration) {
        RestrictionDuration = restrictionDuration;
    }

    public BigDecimal getTransactionRestrictionLimit() {
        return TransactionRestrictionLimit;
    }

    public void setTransactionRestrictionLimit(BigDecimal transactionRestrictionLimit) {
        TransactionRestrictionLimit = transactionRestrictionLimit;
    }

    public BigDecimal getCommissionForTransactions() {
        return CommissionForTransactions;
    }

    public void setCommissionForTransactions(BigDecimal commissionForTransactions) {
        CommissionForTransactions = commissionForTransactions;
    }
    public Bank getBank() {
        return Bank;
    }

    /**
     * toString override
     * @return String string interpretation of config
     */

    public String toString() {
        return String.format("Debit interest on the balance: %f\nCredit commission: %f\nDeposit percents:\n%s\nCommission for transaction: %f", DebitPercent, CreditCommission, getStringDepositConfig(), CommissionForTransactions);
    }

    private String getStringDepositConfig() {
        String totalString = " ";
        for (Map.Entry<BigDecimal, BigDecimal> pair: _percentsForDepositAccounts.entrySet()) {
            totalString += pair.getKey() + "+" + " - " + pair.getValue() + "\n";
        }
        return totalString;
    }
}
