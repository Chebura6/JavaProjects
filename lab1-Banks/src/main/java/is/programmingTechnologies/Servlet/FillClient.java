package is.programmingTechnologies.Servlet;

import is.programmingTechnologies.Accounts.CreditAccount;
import is.programmingTechnologies.Accounts.DebitAccount;
import is.programmingTechnologies.Accounts.DepositAccount;
import is.programmingTechnologies.Exceptions.ServiceViewException;
import is.programmingTechnologies.Interfaces.IAccount;
import is.programmingTechnologies.Interfaces.ITimeManager;
import is.programmingTechnologies.Models.Client;
import is.programmingTechnologies.Services.CentralBank;
import is.programmingTechnologies.Servlet.DTO.ClientDTO;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * This class work with JFrame window and let user fill client parameters during creating new client.
 *
 * @version 1.0.0 23.02.2023
 */

public class FillClient {
    private ClientDTO _clientDTO;
    private WindowSettings _windowSettings;
    private CentralBank _centralBank;
    private ITimeManager _timeManager;
    public ClientDTO getClientDTO() {
        return _clientDTO;
    }

    /**
     * Class responsible for filling new client parameters constructor
     * @param windowSettings
     * @param centralBank
     * @param timeManager
     */

    public FillClient(WindowSettings windowSettings, CentralBank centralBank, ITimeManager timeManager) {
        if (windowSettings == null) throw new NullPointerException();
        _windowSettings = windowSettings;
        _clientDTO = new ClientDTO();
        _centralBank = centralBank;
        _timeManager = timeManager;
    }

    /**
     * Start filling client parameters algorithm
     * @throws Exception
     */

    public void fill() {
        fillName();
    }

    /**
     * Fill client dto name
     */

    private void fillName() {
        _windowSettings.setNewParameter("Input client name");
        _windowSettings.getOk().addActionListener(l -> {
            _clientDTO.setName(_windowSettings.getTextArea().getText());
            fillSurname();
        });
    }

    /**
     * Fill client dto surname
     */

    private void fillSurname() {
        _windowSettings.setNewParameter("Input client surname", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(e -> {
            _clientDTO.setSurname(_windowSettings.getTextArea().getText());
            fillPassport();
        });
    }

    /**
     * Fill client dto passport
     */

    private void fillPassport() {
        _windowSettings.setNewParameter("Input passport", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(e -> {
            _clientDTO.setPassport(_windowSettings.getTextArea().getText());
            fillAddress();
        });
    }

    /**
     * Fill client dto address
     */

    private void fillAddress() {
        _windowSettings.setNewParameter("Input address", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(e -> {
            _clientDTO.setAddress(_windowSettings.getTextArea().getText());
            chooseAccount();
//            _windowSettings.removeButton(_windowSettings.getOk());
//            _windowSettings.setStockView();

        });
    }

    /**
     * Choosing account type
     */

    private void chooseAccount() {
        _windowSettings.setNewParameter("Input account type (Debit, Credit, Deposit)", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(l -> {

            String choice = _windowSettings.getTextArea().getText();
            IAccount account = null;
            try {
                if (choice.equals("Deposit") || choice.equals("deposit")) {
                    _clientDTO.setAccountType(new DepositAccount());
                }
                else if (choice.equals("Debit") || choice.equals("debit")) {
                    _clientDTO.setAccountType(new DebitAccount());
                }
                else if (choice.equals("Credit") || choice.equals("credit")){
                    _clientDTO.setAccountType(new CreditAccount());
                }
                else {
                    throw ServiceViewException.InvalidInput();
                }
            }
            catch (ServiceViewException ex) {
                throw new RuntimeException();
            }
            addClientInBank();
        });
    }

    /**
     * Adding new client into a bank
     */

    private void addClientInBank() {
        String bankChoose = "Choose a  bank: ";
        List<String> banks = _centralBank.getBankNamesInSystem();
        for (var bankName : banks) {
            bankChoose = bankChoose + " " + bankName + ",";
        }

        bankChoose = bankChoose.substring(0, bankChoose.length() - 1);

        _windowSettings.setNewParameter(bankChoose, _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(l -> {
            String chosenBankName = _windowSettings.getTextArea().getText();

            if (_clientDTO.getPassport() == "-") {
                if (_clientDTO.getAddress() == "-") {
                    _centralBank.addClient(Client.builder()
                                    .withName(_clientDTO.getName())
                                    .withSurname(_clientDTO.getSurname()).build(),
                            chosenBankName, _clientDTO.getAccountType(), _timeManager.getCurrentTime());
                } else {
                    _centralBank.addClient(
                            Client.builder()
                                    .withName(_clientDTO.getName())
                                    .withSurname(_clientDTO.getSurname())
                                    .withAddress(_clientDTO.getAddress()).build(),
                            chosenBankName, _clientDTO.getAccountType(), _timeManager.getCurrentTime());
                }
            } else {
                if (_clientDTO.getAddress() == "-") {
                    _centralBank.addClient(
                            Client.builder()
                                    .withName(_clientDTO.getName())
                                    .withSurname(_clientDTO.getSurname())
                                    .withPassport(_clientDTO.getPassport()).build(),
                            chosenBankName, _clientDTO.getAccountType(), _timeManager.getCurrentTime());
                } else {
                    _centralBank.addClient(
                             Client.builder()
                                    .withName(_clientDTO.getName())
                                    .withSurname(_clientDTO.getSurname())
                                    .withAddress(_clientDTO.getAddress())
                                    .withPassport(_clientDTO.getPassport()).build(),
                            chosenBankName, _clientDTO.getAccountType(), _timeManager.getCurrentTime());
                }
            }
            topupDepositAccount();
        });
    }

    /**
     * Topup deposit account
     */

    private void topupDepositAccount() {
        if (_clientDTO.getAccountType() instanceof DepositAccount) {
            _clientDTO.getAccountType().setAccountCreationTime(_timeManager.getCurrentTime());

            _windowSettings.setNewParameter("Would you like topup your new account?(yes/no)", _windowSettings.getOk());
            _windowSettings.getOk().addActionListener(l -> {
                if (_windowSettings.getTextArea().getText().equals("Yes") || _windowSettings.getTextArea().getText().equals("yes")) {

                    _windowSettings.setNewParameter("Which account do you want to transfer money from?", _windowSettings.getOk());
                    _windowSettings.removeJTextArea(_windowSettings.getTextArea());
                    _windowSettings.setTransactionMenu();
                    _windowSettings.get_additionalText().setText("Input here");
                    _windowSettings.get_additionalText2().setText(_centralBank.getClientAccountsInfo());
                    _windowSettings.getOk().addActionListener(k -> {

                        UUID fromID = UUID.fromString(_windowSettings.get_additionalText().getText());
                        UUID toID = _clientDTO.getAccountType().getAccountID();
                        _windowSettings.clearTransactionMenu();
                        _windowSettings.setNewParameter("Input amount", _windowSettings.getOk());
                        _windowSettings.getOk().addActionListener(r -> {

                            BigDecimal amount = new BigDecimal(_windowSettings.getTextArea().getText());
                            try {
                                if (_centralBank.topupInitialAmount(fromID, toID, amount)) {
                                    _windowSettings.setNewParameter("Transaction is successful", _windowSettings.getOk());
                                    _windowSettings.getOk().addActionListener(s -> {
                                        _windowSettings.removeButton(_windowSettings.getOk());
                                        _windowSettings.setStockView();
                                        _windowSettings.setStockButtons();
                                    });
                                } else {
                                    _windowSettings.setNewParameter("Transaction failed", _windowSettings.getOk());
                                    _windowSettings.getOk().addActionListener(s -> {
                                        _windowSettings.removeButton(_windowSettings.getOk());
                                        _windowSettings.setStockView();
                                        _windowSettings.setStockButtons();
                                    });
                                }
                            } catch (Exception ex) {
                                throw new RuntimeException();
                            }
                        });
                    });
                } else {
                    _windowSettings.removeButton(_windowSettings.getOk());
                    setStockView();
                }
            });
        } else {
            _windowSettings.removeButton(_windowSettings.getOk());
            setStockView();
        }
    }

    /**
     * Sets default view of window
     */

    private void setStockView() {
        _windowSettings.setStockView();
        _windowSettings.setStockButtons();
    }
}
