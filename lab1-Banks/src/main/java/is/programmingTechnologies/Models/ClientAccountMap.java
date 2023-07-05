package is.programmingTechnologies.Models;

import is.programmingTechnologies.Interfaces.IAccount;

/**
 * This class is made for mapping clients with their accounts inside a bank.
 *
 * @version 1.0.0 23.02.2023
 */

public class ClientAccountMap {
    Client Client;
    IAccount Account;

    /**
     * Client account map object constructor
     * @param client client to map
     * @param account account to map
     */
    public ClientAccountMap(Client client, IAccount account)
    {
        if (client == null) throw new NullPointerException();
        if (account == null) throw new NullPointerException();
        Client = client;
        Account = account;
    }

    public Client getClient() {
        return Client;
    }

    public void setClient(Client client) {
        Client = client;
    }

    public IAccount getAccount() {
        return Account;
    }

    public void setAccount(IAccount account) {
        Account = account;
    }

    /**
     * toString override
     * @return
     */

    public String ToString()
    {
        return Client.toString() + Account.toString();
    }

}
