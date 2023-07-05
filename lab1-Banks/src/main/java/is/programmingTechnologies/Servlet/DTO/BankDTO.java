package is.programmingTechnologies.Servlet.DTO;

/**
 * This class is only for storing data for creating a real object in system.
 * It stores parameters for creating a real bank object in central bank.
 *
 * @version 1.0.0 23.02.2023
 */

public class BankDTO {
    private String _bankName;
    private ConfigDTO _configDTO;

    public ConfigDTO getСonfigDTO() {
        return _configDTO;
    }

    public void setСonfigDTO(ConfigDTO _configDTO) {
        this._configDTO = _configDTO;
    }

    public String getBankName() {
        return _bankName;
    }

    public void setBankName(String bankName) {
        _bankName = bankName;
    }
}
