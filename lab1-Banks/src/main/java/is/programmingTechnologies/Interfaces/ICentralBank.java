package is.programmingTechnologies.Interfaces;

import is.programmingTechnologies.Entities.Bank;
import is.programmingTechnologies.Exceptions.ConfigException;
import is.programmingTechnologies.Models.Client;
import is.programmingTechnologies.Models.Config;
import is.programmingTechnologies.Servlet.DTO.BankDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Central bank interface.
 *
 * @version 1.0.0 23.02.2023
 */

public interface ICentralBank {
    void addClient(Client client, String bankName, IAccount account, LocalDateTime currentTime);
    Bank addBank(BankDTO bankDTO) throws NullPointerException, ConfigException, Exception;
    void calculatePercentages();
    void changeAccountsTime(LocalDateTime dateTime);
    void chargeInterest();
    void timeHasBeenDecreased(LocalDateTime newTime);
    public void changeConfig(String bankName, Config config);
    String getConfig(String bankName);
    boolean transfer(IAccount from, IAccount toAccount, BigDecimal amount) throws Exception;
    boolean transfer(UUID fromGuid, UUID toGuid, BigDecimal amount) throws Exception;

}
