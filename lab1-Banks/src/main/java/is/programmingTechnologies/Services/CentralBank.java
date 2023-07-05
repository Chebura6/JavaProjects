package is.programmingTechnologies.Services;

import is.programmingTechnologies.Commands.*;
import is.programmingTechnologies.Entities.Bank;
import is.programmingTechnologies.Exceptions.ConfigException;
import is.programmingTechnologies.Interfaces.IAccount;
import is.programmingTechnologies.Interfaces.ICentralBank;
import is.programmingTechnologies.Models.Client;
import is.programmingTechnologies.Models.Config;
import is.programmingTechnologies.Servlet.DTO.BankDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * This class is the main "service" class, it manages all queries to system.
 *
 * @version 1.0.0 23.02.2023
 */

public class CentralBank implements ICentralBank {
    public ArrayList<Bank> getBanks() {
        return Banks;
    }
    private ArrayList<Bank> Banks;

    /**
     * Central bank constructor
     */

    public CentralBank() {
        Banks = new ArrayList<Bank>();
    }

    /**
     * Add client in the system
     * @param client client to add
     * @param bankName which bank to add client in
     * @param account account to associate with client
     * @param currentTime current time
     */

    public void addClient(Client client, String bankName, IAccount account, LocalDateTime currentTime) {
        if (client == null) throw new NullPointerException("Null client!");
        if (account == null) throw new NullPointerException("Null account!");

        Bank bank = findBank(bankName);
        account.setAccountCreationTime(currentTime);
        if (client.Passport != null && client.Address != null) {
            account.setTransactionRestrictionLimit(new BigDecimal(-1));
        }
        else {
            account.setTransactionRestrictionLimit(bank.getConfig().getTransactionRestrictionLimit());
            account.setTransactionRestrictionUsed(new BigDecimal(0));
        }

        bank.addAccount(client, account);
    }

    /**
     * Change time for all accounts
     */

    public void changeAccountsTime(LocalDateTime dateTime) {
        for (Bank bank : Banks) {
            bank.setAccountsTime(dateTime);
        }
    }

    /**
     * Charge interests on all accounts
     */

    public void chargeInterest() {
        for (Bank bank : Banks) {
            bank.chargeInterests();
        }
    }


    /**
     * Calculate percentages for all accounts
     */

    public void calculatePercentages() {
        for (Bank bank : Banks) {
            bank.calculateThePercentageOnTheBalance();
        }
    }

    /**
     * Add bank in the system
     * @param bankDTO dto for creating new bank
     * @return bank added bank
     * @throws NullPointerException
     * @throws ConfigException
     * @throws Exception
     */

    public Bank addBank(BankDTO bankDTO) throws  NullPointerException, ConfigException, Exception {
        if (bankDTO == null) throw new NullPointerException();
        var bank = new Bank(bankDTO.getBankName(), new Config(bankDTO.getСonfigDTO().getDebitPercent(), bankDTO.getСonfigDTO().getLowerBounds(),
                bankDTO.getСonfigDTO().getPercents(), bankDTO.getСonfigDTO().getCreditPercent(), bankDTO.getСonfigDTO().getRestrictionDuration(),
                bankDTO.getСonfigDTO().getTransactionRestrictionLimit(), bankDTO.getСonfigDTO().getCommissionForTransactions()));
        Banks.add(bank);
        return bank;
    }

    /**
     * Change config for certain bank
     * @param bankName bank name which config needs change
     * @param config new config object to replace an old one
     */

    public void changeConfig(String bankName, Config config)
    {
        if (bankName == null) throw new NullPointerException();
        if (config == null) throw new NullPointerException();
        Bank bank = findBank(bankName);
        bank.setConfig(config);
    }

    /**
     * Find certain config using bank name
     * @param bankName
     * @return String string represent of config
     */

    public String getConfig(String bankName)
    {
        return findBank(bankName).getConfig().toString();
    }

    /**
     * Transaction method
     * @param from which account to withdraw money from
     * @param toAccount which account to withdraw money to
     * @param amount amount to transfer
     * @return boolean is transaction successful
     * @throws Exception
     */

    public boolean transfer(IAccount from, IAccount toAccount, BigDecimal amount) throws Exception {
        if (from == null) throw new NullPointerException();
        if (toAccount== null) throw new NullPointerException();
        var ctx = new TransactionContext(from, toAccount, amount);
        var withdraw = new TransactionHandler(new WithdrawCommand(ctx));
        var commission = new TransactionHandler(new CommissionCommand(ctx));
        var topup = new TransactionHandler(new TopupCommand(ctx));
        return new TransactionHandlerChain(withdraw, topup).Execute();
    }

    /**
     * Set initial amount on deposit account
     * @param fromGuid which account to withdraw money from
     * @param toGuid which account to withdraw money to
     * @param amount amount to transfer
     * @return boolean is transaction successful
     * @throws Exception
     */

    public boolean topupInitialAmount(UUID fromGuid, UUID toGuid, BigDecimal amount) throws Exception {
        if (fromGuid == null) throw new NullPointerException();
        if (toGuid == null) throw new NullPointerException();
        var from = findAccount(fromGuid);
        var to = findAccount(toGuid);
        var ctx = new TransactionContext(from, to, amount);
        var withdraw = new TransactionHandler(new WithdrawCommand(ctx));
        var commission = new TransactionHandler(new CommissionCommand(ctx));
        var topup = new TransactionHandler(new InitialAmountCommand(ctx));
        return new TransactionHandlerChain(withdraw, commission, topup).Execute();
    }

    /**
     * Transfer money using accounts UUIDs
     * @param fromGuid which UUID to withdraw money from
     * @param toGuid which UUID to withdraw money to
     * @param amount amount to transfer
     * @return
     * @throws Exception
     */

    public boolean transfer(UUID fromGuid, UUID toGuid, BigDecimal amount) throws Exception {
        if (fromGuid == null) throw new NullPointerException();
        if (toGuid == null) throw new NullPointerException();
        var from = findAccount(fromGuid);
        var to = findAccount(toGuid);
        var ctx = new TransactionContext(from, to, amount);
        var withdraw = new TransactionHandler(new WithdrawCommand(ctx));
        var commission = new TransactionHandler(new CommissionCommand(ctx));
        var topup = new TransactionHandler(new TopupCommand(ctx));
        return new TransactionHandlerChain(withdraw, commission, topup).Execute();
    }

    /**
     * Find account using UUID
     * @param accountID account's UUID to find
     * @return IAccount
     */

    public IAccount findAccount(UUID accountID) {
        return Banks.stream()
                .flatMap(b -> b.getAccounts().stream())
                .filter(a -> accountID.equals(a.getAccount().getAccountID()))
                .findFirst()
                .orElse(null)
                .getAccount();
    }

    /**
     * Notify accounts what time has been decreased
     * @param newTime
     */

    public void timeHasBeenDecreased(LocalDateTime newTime) {
        for (Bank bank : Banks) {
            bank.removeAccountsFromFuture(newTime);
        }
    }

    /**
     * Get information about clients and their accounts
     * @return String string with clients and their accounts
     */

    public String getClientAccountsInfo() {
        String totalString = new String();
        for (Bank bank : Banks) {
            totalString += bank.toString();
        }

        totalString = totalString.substring(1);
        return totalString;
    }

    /**
     * Get banks names in system
     * @return List<String> banks names
     */

    public List<String> getBankNamesInSystem()
    {
        return Banks.stream()
                .map(l -> {
                    return l.getName();
                })
                .toList();
    }

    /**
     * Finds certain bank using name
     * @param bankName bank name
     * @return Bank
     */

    private Bank findBank(String bankName)
    {
        return Banks.stream().filter(bank -> bankName.equals(bank.getName()))
                .findFirst()
                .orElse(null);
    }
}
