package is.programmingTechnologies.Servlet;

import is.programmingTechnologies.Services.CentralBank;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Class for invoking transaction
 */

public class MakeTransaction {
    private CentralBank _centralBank;
    private WindowSettings _windowSettings;
    public MakeTransaction(CentralBank centralBank, WindowSettings windowSettings) {
        _centralBank = centralBank;
        _windowSettings = windowSettings;
    }

    /**
     * Trainsaction invoke method
     */

    public void init() {
        _windowSettings.setTransactionMenu();
        _windowSettings.get_additionalText().setText("Input here");
        _windowSettings.get_additionalText2().setText(_centralBank.getClientAccountsInfo());
        _windowSettings.setNewParameter("Which account do you want to transfer money from?");
        _windowSettings.getOk().addActionListener(k -> {
            UUID fromID = UUID.fromString(_windowSettings.get_additionalText().getText());
//                        _windowSettings.setTransactionMenu("Which account do you want to transfer money to?", _windowSettings.getOk());
//            _windowSettings.getInfo().setText("Which account do you want to transfer money to?");
            _windowSettings.get_additionalText().setText("Input here");
//            _windowSettings.removeButton(_windowSettings.getOk());
//            _windowSettings.setOk(new JButton("Ok"));
            _windowSettings.setNewParameter("Which account do you want to transfer money to?", _windowSettings.getOk());
//            _windowSettings.getPanel().add(_windowSettings.getOk(), BorderLayout.SOUTH);
            _windowSettings.getOk().addActionListener(a -> {

                UUID toID = UUID.fromString(_windowSettings.get_additionalText().getText());
                _windowSettings.clearTransactionMenu();
                _windowSettings.setNewParameter("Input amount", _windowSettings.getOk());
                _windowSettings.getOk().addActionListener(r -> {
                    BigDecimal amount = new BigDecimal(_windowSettings.getTextArea().getText());
                    try {
                        if (_centralBank.transfer(fromID, toID, amount)) {
                            _windowSettings.setNewParameter("Transaction is successful", _windowSettings.getOk());
                            _windowSettings.getOk().addActionListener(s -> {
                                _windowSettings.setStockView();
                                _windowSettings.setStockButtons();
                            });
                        } else {
                            _windowSettings.setNewParameter("Transaction failed", _windowSettings.getOk());
                            _windowSettings.getOk().addActionListener(s -> {
                                _windowSettings.setStockView();
                                _windowSettings.setStockButtons();
                            });
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException();
                    }
                });
            });
        });
    }
}
