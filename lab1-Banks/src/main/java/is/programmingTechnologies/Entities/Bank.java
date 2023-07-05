package is.programmingTechnologies.Entities;

import is.programmingTechnologies.Accounts.DepositAccount;
import is.programmingTechnologies.Interfaces.IAccount;
import is.programmingTechnologies.Models.Client;
import is.programmingTechnologies.Models.ClientAccountMap;
import is.programmingTechnologies.Models.Config;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * This class encapsulate all bank logic.
 *
 * @version 1.0.0 23.02.2023
 */

public class Bank {

    public String Name;
    Config Config;

    ArrayList<ClientAccountMap> Accounts;

    /**
     * Bank constructor
     *
     * @param name name of bank
     * @param config configuration object
     */

    public Bank(String name, Config config)
    {
        if (name == null) throw new NullPointerException();
        if (config == null) throw new NullPointerException();
        Name = name;
        Config = config;
        Accounts = new ArrayList<ClientAccountMap>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Config getConfig() {
        return Config;
    }

    public ArrayList<ClientAccountMap> getAccounts() {
        return Accounts;
    }

    public void setConfig(Config config) {
        Config = config;
    }

    /**
     * Calculate percents for all accounts
     */

    public void calculateThePercentageOnTheBalance()
    {
        for (ClientAccountMap account : Accounts)
        {
            account.getAccount().calculatePercentages();
        }
    }

    /**
     * Equals override
     * @param obj
     * @return boolen are equals
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bank bank = (Bank) obj;
        return Objects.equals(Name, bank.Name);
    }

    /**
     * toString override
     * @return
     */

    public String toString() {
        String totalString = new String();
        for (ClientAccountMap clientAccountMap : Accounts) {
            totalString += "\n" + getName() + "\n";
            totalString += clientAccountMap.getClient().toString() + clientAccountMap.getAccount().toString();
        }

        return totalString;
    }

    /**
     * Add account in system
     * @param client add new account owner
     * @param account add new account
     */

    public void addAccount(Client client, IAccount account) {
        if (client == null) throw new NullPointerException("Null client");
        if (account == null) throw new NullPointerException("Null account");
        account.fillWithData(Config);
        Accounts.add(new ClientAccountMap(client, account));
    }

    /**
     * Charge interests on all accounts
     */

    public void chargeInterests() {
        for (ClientAccountMap account : Accounts) {
            account.getAccount().chargeInterests();
        }
    }

    /**
     * Set time for all accounts
     * @param dateTime time to set
     */

    public void setAccountsTime(LocalDateTime dateTime) {
        for (ClientAccountMap clientAccountMap : Accounts) {
            if (clientAccountMap.getAccount() instanceof DepositAccount){
                clientAccountMap.getAccount().setTime(dateTime);
            }
        }
    }

    /**
     * Remove created in the future accounts
     * @param currentTime
     */

    public void removeAccountsFromFuture(LocalDateTime currentTime) {
        var newAccounts = new ArrayList<ClientAccountMap>(Accounts);
        for (ClientAccountMap clientAccountMap : Accounts) {
            if (clientAccountMap.getAccount().getAccountCreationTime().compareTo(currentTime) >  0) {
                newAccounts.remove(clientAccountMap);
            }
        }

        Accounts = newAccounts;
    }
}
