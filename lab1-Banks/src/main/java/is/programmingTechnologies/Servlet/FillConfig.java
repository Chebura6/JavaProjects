package is.programmingTechnologies.Servlet;

import is.programmingTechnologies.Exceptions.BankDTOException;
import is.programmingTechnologies.Servlet.DTO.ConfigDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

/**
 * This class work with JFrame window and let user fill config parameters during creating new bank.
 *
 * @version 1.0.0 23.02.2023
 */

public class FillConfig {
    private WindowSettings _windowSettings;
    private FillBankParams _fillBankParams;
    private ConfigDTO _configDTO;

    public ConfigDTO get_configDTO() {
        return _configDTO;
    }

    /**
     * Class responsible for filling config parameters into config dto
     * @param parameters
     * @param fillBankParams
     */

    public FillConfig(WindowSettings parameters, FillBankParams fillBankParams) {
        if (parameters == null) throw new NullPointerException("Null window parameters");
        _windowSettings = parameters;
        _configDTO = new ConfigDTO();
        _fillBankParams = fillBankParams;
    }

    /**
     * Start filling config parameters algorithm
     * @throws Exception
     */

    public void fill() {
        getDebitPercent();
    }

    /**
     * Get debit percent for config dto
     */

    private void getDebitPercent() {
        _windowSettings.setNewParameter("Input debit percent", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(e -> {
            _configDTO.setDebitPercent(new BigDecimal(_windowSettings.getTextArea().getText()));
            getLowerBounds();
        });
    }

    /**
     * Get lower bounds for config dto
     */

    private void getLowerBounds() {
        _windowSettings.setNewParameter("What's lower bounds? You need to enter " + _windowSettings.CountOfBounds +  " numbers.", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(l -> {
            String[] splitted = _windowSettings.getTextArea().getText().split(" ");
            List<BigDecimal> decimals = Arrays.stream(splitted)
                    .map(u -> {
                        return new BigDecimal(u);
                    })
                    .toList();
            _configDTO.setLowerBounds(decimals);
            getPercents();
        });
    }

    /**
     * Get percents associated with lower bounds
     */

    private void getPercents() {
        _windowSettings.setNewParameter("What's percents? You need to enter " + _windowSettings.CountOfBounds +  " numbers.", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(l -> {
            String[] splitted = _windowSettings.getTextArea().getText().split(" ");
            List<BigDecimal> decimals = Arrays.stream(splitted)
                    .map(u -> {
                        return new BigDecimal(u);
                    })
                    .toList();
            _configDTO.setPercents(decimals);
            getCreditCommission();
        });
    }

    /**
     * Get credit commission for config dto
     */

    private void getCreditCommission() {
        _windowSettings.setNewParameter("What's credit percent?", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(l -> {
            try {
                _configDTO.setCreditPercent(new BigDecimal(_windowSettings.getTextArea().getText()));
                getRestrictionDuration();
            } catch (BankDTOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Get restriction duration fo config dto
     */

    private void getRestrictionDuration() {
        _windowSettings.setNewParameter("What's restriction duration? Format: years, months, days.", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(l -> {
            try {
                String[] splitted = _windowSettings.getTextArea().getText().split(" ");
                if (splitted.length != 3) throw new RuntimeException("Invalid count of parameters");
                LocalDate futureDate = LocalDate.now();
                futureDate = futureDate.plusYears(Integer.parseInt(splitted[0]));
                futureDate = futureDate.plusMonths(Integer.parseInt(splitted[1]));
                futureDate = futureDate.plusDays(Integer.parseInt(splitted[2]));
//                        LocalDate.of(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
                _configDTO.setRestrictionDuration(Period.between(LocalDate.now(), futureDate));
                getTransactionRestrictionLimit();
            } catch (BankDTOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Get transaction restriction limit
     */

    private void getTransactionRestrictionLimit()
    {
        _windowSettings.setNewParameter("What's transaction restriction limit?", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(e -> {
            _configDTO.setTransactionRestrictionLimit(new BigDecimal(_windowSettings.getTextArea().getText()));
            getCommissionForTransactions();
        });
    }

    /**
     * Get commission for transactions for config dto
     * @throws RuntimeException
     */

    private void getCommissionForTransactions() throws RuntimeException {
        _windowSettings.setNewParameter("What's commission for transactions?", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(e -> {
            _configDTO.setCommissionForTransactions(new BigDecimal(_windowSettings.getTextArea().getText()));
            try {
                _fillBankParams.setConfig();
                _windowSettings.removeButton(_windowSettings.getOk());
                _windowSettings.setStockView();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
