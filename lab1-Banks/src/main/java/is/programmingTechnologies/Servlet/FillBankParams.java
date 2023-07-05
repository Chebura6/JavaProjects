package is.programmingTechnologies.Servlet;

import is.programmingTechnologies.Exceptions.BanksServletException;
import is.programmingTechnologies.Exceptions.ConfigException;
import is.programmingTechnologies.Services.CentralBank;
import is.programmingTechnologies.Servlet.DTO.BankDTO;

/**
 * This class work with JFrame window and let user fill bank dto.
 *
 * @version 1.0.0 23.02.2023
 */

public class FillBankParams {
    private WindowSettings _windowSettings;
    private FillConfig _fillConfig;
    public BankDTO BankDTO;
    private CentralBank _centralBank;

    public BankDTO getBankDTO() {
        return BankDTO;
    }

    public void setBankDTO(BankDTO bankDTO) {
        BankDTO = bankDTO;
    }

    /**
     * Class responsible for filling new bank parameters constructor
     * @param parameters window parameters
     * @param centralBank central bank
     */

    public FillBankParams(WindowSettings parameters, CentralBank centralBank) {
        _windowSettings = parameters;
        BankDTO = new BankDTO();
        _fillConfig = new FillConfig(parameters, this);
        _centralBank = centralBank;
    }

    /**
     * Start filling bank parameters algorithm
     * @throws Exception
     */

    public void fill() throws Exception {
        getBankName();
    }

    /**
     * Set into bank dto bank name
     * @throws BanksServletException
     */

    private void getBankName() throws BanksServletException {
        _windowSettings.setNewParameter("Input bank name");
        _windowSettings.getOk().addActionListener(e -> {
            BankDTO.setBankName(_windowSettings.getTextArea().getText());
            _fillConfig.fill();
        });
    }

    /**
     * Set into bank dto config
     * @throws BanksServletException
     */

    void setConfig() {
        BankDTO.setСonfigDTO(_fillConfig.get_configDTO());
        addBankInCentralBank();
    }

    /**
     * Set filled bank dto in central bank
     * @throws BanksServletException
     */

    void addBankInCentralBank() throws NullPointerException {
        try {
            _centralBank.addBank(BankDTO);
        } catch(Exception ex) {
            throw new RuntimeException();
        }

        _windowSettings.setStockView();
        _windowSettings.setStockButtons();
    }
}
