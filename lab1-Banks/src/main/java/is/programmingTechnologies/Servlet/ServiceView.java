package is.programmingTechnologies.Servlet;

import is.programmingTechnologies.Interfaces.ITimeManager;
import is.programmingTechnologies.Services.CentralBank;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The main class of servlet part, JFrame successor.
 *
 * @version 1.0.0 23.02.2023
 */

public class ServiceView extends JFrame {

    private CentralBank _centralBank;
    private WindowSettings _windowSettings;

    public CentralBank getCentralBank() {
        return _centralBank;
    }

    /**
     * Main servlet service class constructor
     * @param centralBank
     */

    public ServiceView(CentralBank centralBank) {
        if (centralBank == null) throw new NullPointerException();
        _centralBank = centralBank;
        _windowSettings = new WindowSettings(this);
    }

    /**
     * Initialize service
     * @param timeManager
     */

    public void init(ITimeManager timeManager) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 400);

        _windowSettings.setPanel(new JPanel());
        _windowSettings.setTextArea(new JTextArea());
        _windowSettings.setAddBank(new JButton("Add bank"));
        _windowSettings.setAddClient(new JButton("Add client"));
        _windowSettings.setGetConfig(new JButton("Get config"));
        _windowSettings.setMakeTransaction(new JButton("Make transaction"));
        _windowSettings.setGetSystemState(new JButton("Get system state"));
        _windowSettings.setIncreaseTimeForOneHour(new JButton("Increase time for 1 hour"));
        _windowSettings.setInfo(new JLabel("Lets start"));
        getContentPane().invalidate();
        getContentPane().validate();

        add(_windowSettings.getTextArea(), BorderLayout.CENTER);
        add(_windowSettings.getPanel(), BorderLayout.SOUTH);
        _windowSettings.getPanel().add(_windowSettings.getAddBank(), BorderLayout.CENTER);
        _windowSettings.getPanel().add(_windowSettings.getAddClient(), BorderLayout.CENTER);
        _windowSettings.getPanel().add(_windowSettings.getGetConfig(), BorderLayout.CENTER);
        _windowSettings.getPanel().add(_windowSettings.getGetSystemState(), BorderLayout.CENTER);
        _windowSettings.getPanel().add(_windowSettings.getMakeTransaction(), BorderLayout.CENTER);
        _windowSettings.getPanel().add(_windowSettings.getIncreaseTimeForOneHour(), BorderLayout.CENTER);
        add(_windowSettings.getInfo(), BorderLayout.NORTH);
        setVisible(true);
        revalidate();
        repaint();


        _windowSettings.getAddBank().addActionListener(e -> {
            _windowSettings.getTextArea().setText("");
            revalidate();
            repaint();
            var bank = new FillBankParams(_windowSettings, _centralBank);
            try {
                bank.fill();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        _windowSettings.getAddClient().addActionListener(e -> {
            _windowSettings.getTextArea().setText("");
            var fillClient = new FillClient(_windowSettings, _centralBank, timeManager);
            fillClient.fill();
        });
        _windowSettings.getGetSystemState().addActionListener(l -> {
            _windowSettings.getTextArea().setText("");
            _windowSettings.removeStockButtons();
            if (_centralBank == null) throw new RuntimeException("No one central bank in system!");
            _windowSettings.getTextArea().setText(_centralBank.getClientAccountsInfo());
            JScrollPane scrollPane = new JScrollPane(_windowSettings.getTextArea());
            add(scrollPane, BorderLayout.CENTER);
            _windowSettings.setNewParameter("Actual system state:");
            _windowSettings.getOk().addActionListener(a -> {
                scrollPane.removeAll();
                remove(scrollPane);
                add(_windowSettings.getTextArea());
                _windowSettings.removeButton(_windowSettings.getOk());
                _windowSettings.setStockView();
                _windowSettings.setStockButtons();
            });
        });
        _windowSettings.getMakeTransaction().addActionListener(l -> {
            _windowSettings.removeStockButtons();
            _windowSettings.removeJTextArea(_windowSettings.getTextArea());
            MakeTransaction transaction = new MakeTransaction(_centralBank, _windowSettings);
            transaction.init();
        });
        _windowSettings.getGetConfig().addActionListener(l -> {
            _windowSettings.getTextArea().setText("");
            GetBankConfig getConfig = new GetBankConfig(_windowSettings, _centralBank, this);
            getConfig.getConfig();
        });
        _windowSettings.getIncreaseTimeForOneHour().addActionListener(l -> {
            _windowSettings.removeStockButtons();
            _windowSettings.setNewParameter("Current time:");
            LocalDateTime time = timeManager.getCurrentTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), time.getHour(), time.getMinute());
            String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"
            _windowSettings.getTextArea().setText(formattedDateTime);
            _windowSettings.getOk().addActionListener(a -> {
                _windowSettings.removeButton(_windowSettings.getOk());
                _windowSettings.setStockButtons();
                _windowSettings.setStockView();
            });
        });
    }
}
