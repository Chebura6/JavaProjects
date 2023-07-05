package is.programmingTechnologies.Servlet;

import is.programmingTechnologies.Accounts.CreditAccount;
import is.programmingTechnologies.Accounts.DebitAccount;
import is.programmingTechnologies.Accounts.DepositAccount;
import is.programmingTechnologies.Exceptions.ServiceViewException;
import is.programmingTechnologies.Interfaces.IAccount;

/**
 * This class work with JFrame window and let user choose an account during creating new client.
 *
 * @version 1.0.0 23.02.2023
 */

public class AccountChooser {
    private WindowSettings _windowSettings;
    private IAccount _account;

    /**
     * Account responsible for choosing account type constructor
     * @param windowSettings
     */

    public AccountChooser(WindowSettings windowSettings) {
        if (windowSettings == null) throw new NullPointerException();
        _windowSettings = windowSettings;
    }

    /**
     * Making choice
     * @return IAccount chosed account
     */

    public IAccount makeChoice() {
        _windowSettings.setNewParameter("Input account type (Debit, Credit, Deposit)", _windowSettings.getOk());
        _windowSettings.getOk().addActionListener(l -> {

            String choice = _windowSettings.getTextArea().getText();
            IAccount account = null;
            try {
                if (choice == "Deposit" || choice == "deposit") {
                    _account = new DepositAccount();
                } else if (choice == "Debit" || choice == "debit") {
                    _account = new DebitAccount();
                } else if (choice == "Credit" || choice == "credit"){
                    _account = new CreditAccount();
                } else {
                    throw ServiceViewException.InvalidInput();
                }
            }
            catch (ServiceViewException ex) {
                throw new RuntimeException();
            }
        });

        return _account;
    }
}
