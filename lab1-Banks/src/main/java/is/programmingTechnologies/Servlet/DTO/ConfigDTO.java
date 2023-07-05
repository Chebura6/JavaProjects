package is.programmingTechnologies.Servlet.DTO;

import is.programmingTechnologies.Exceptions.BankDTOException;

import java.math.BigDecimal;
import java.time.Period;
import java.util.List;

/**
 * This class is only for storing data for creating a real object in system.
 * It stores parameters for creating a config object in central bank.
 *
 * @version 1.0.0 23.02.2023
 */

public class ConfigDTO {
    private BigDecimal _debitPercent;
    private List<BigDecimal> _lowerBounds;
    private List<BigDecimal> _percents;
    private BigDecimal _creditPercent;
    private Period _restrictionDuration;
    private BigDecimal _transactionRestrictionLimit;
    private BigDecimal _commissionForTransactions;

    public ConfigDTO() {

    }

    public BigDecimal getCommissionForTransactions() {
        return _commissionForTransactions;
    }

    public void setCommissionForTransactions(BigDecimal _commissionForTransactions) {
        this._commissionForTransactions = _commissionForTransactions;
    }

    public BigDecimal getTransactionRestrictionLimit() {
        return _transactionRestrictionLimit;
    }

    public void setTransactionRestrictionLimit(BigDecimal _transactionRestrictionLimit) {
        this._transactionRestrictionLimit = _transactionRestrictionLimit;
    }

    public Period getRestrictionDuration() {
        return _restrictionDuration;
    }

    public void setRestrictionDuration(Period restrictionDuration) throws BankDTOException {
        if (restrictionDuration == null) throw BankDTOException.NegativePercent();
        _restrictionDuration = restrictionDuration;
    }

    public List<BigDecimal> getPercents() {
        return _percents;
    }

    public void setPercents(List<BigDecimal> percents) {
        _percents = percents;
    }

    public BigDecimal getCreditPercent() {
        return _creditPercent;
    }

    public void setCreditPercent(BigDecimal creditPercent) throws BankDTOException{
        if (creditPercent.compareTo(new BigDecimal(0)) < 0) throw BankDTOException.NegativePercent();
        _creditPercent = creditPercent;
    }

    public List<BigDecimal> getLowerBounds() {
        return _lowerBounds;
    }

    public void setLowerBounds(List<BigDecimal> lowerBounds) {
        _lowerBounds = lowerBounds;
    }

    public BigDecimal getDebitPercent() {
        return _debitPercent;
    }

    public void setDebitPercent(BigDecimal debitPercent) {
        _debitPercent = debitPercent;
    }
}
