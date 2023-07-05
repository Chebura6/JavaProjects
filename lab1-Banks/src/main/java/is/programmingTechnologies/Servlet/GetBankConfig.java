package is.programmingTechnologies.Servlet;

import is.programmingTechnologies.Services.CentralBank;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * This class show user chosen bank's config
 */

public class GetBankConfig {
    private WindowSettings _windowSettings;
    private CentralBank _centralBank;
    private ServiceView _serviceView;

    /**
     * Class constructor
     * @param windowSettings main wondow parameters
     * @param centralBank CentralBank object
     * @param serviceView service class
     */

    public GetBankConfig(WindowSettings windowSettings, CentralBank centralBank, ServiceView serviceView) {
        _windowSettings = windowSettings;
        _centralBank = centralBank;
        _serviceView = serviceView;
    }

    /**
     * Main method for showing bank config
     */

    public void getConfig() {
        String bankChoose = "Which bank config would you like to see?";
        List<String> banks = _centralBank.getBankNamesInSystem();
        for (var bankName : banks) {
            bankChoose = bankChoose + " " + bankName + ",";
        }

        bankChoose = bankChoose.substring(0, bankChoose.length() - 1);
        _windowSettings.setNewParameter(bankChoose);
        _windowSettings.getOk().addActionListener(a -> {
            System.out.println(_centralBank.getConfig(_windowSettings.getTextArea().getText()));
            JScrollPane scrollPane = new JScrollPane(new JTextArea(_centralBank.getConfig(_windowSettings.getTextArea().getText())));
            _serviceView.remove(_windowSettings.getTextArea());
            _serviceView.add(scrollPane, BorderLayout.CENTER);
            _windowSettings.removeButton(_windowSettings.getOk());
            JButton cancel = new JButton("Cancel");
            _windowSettings.setOk(cancel);
            _windowSettings.getPanel().add(cancel);
            _windowSettings.getInfo().setText("Information:");
            _serviceView.revalidate();
            _serviceView.repaint();
            _windowSettings.getOk().addActionListener(k -> {
                scrollPane.removeAll();
                _serviceView.remove(scrollPane);
                _windowSettings.removeButton(_windowSettings.getOk());
                _serviceView.add(_windowSettings.getTextArea());
                _windowSettings.setStockButtons();
                _windowSettings.setStockView();
            });
        });
    }
}
