package is.programmingTechnologies.Servlet;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * This class is a storage for JFrame objects.
 * Encapsulates all window changes logic.
 *
 * @version 1.0.0 23.02.2023
 */

public class WindowSettings {
    public final long CountOfBounds = 3;
    public final long DateTimeParametersCount = 4;
    private JTextArea _textArea;
    private JLabel _info;
    private JButton _addBank;
    private JButton _addClient;
    private JButton _getConfig;
    private JButton _makeTransaction;
    private JButton _getSystemState;
    private JButton _increaseTimeForOneHour;
    private JPanel _panel;
    private JPanel _additionalPanel;
    private JPanel _additionalPanel2;
    private JButton Ok;
    private ServiceView _serviceView;
    private JScrollPane _scrollPane;

    public JTextArea get_additionalText() {
        return _additionalText;
    }

    public void set_additionalText(JTextArea _additionalText) {
        this._additionalText = _additionalText;
    }

    public JTextArea get_additionalText2() {
        return _additionalText2;
    }

    public void set_additionalText2(JTextArea _additionalText2) {
        this._additionalText2 = _additionalText2;
    }

    private JTextArea _additionalText;
    private JTextArea _additionalText2;

    public void setAddBank(JButton _addBank) {
        this._addBank = _addBank;
    }

    public void setAddClient(JButton _addClient) {
        this._addClient = _addClient;
    }

    public void setGetConfig(JButton _getConfig) {
        this._getConfig = _getConfig;
    }

    public void setMakeTransaction(JButton _makeTransaction) {
        this._makeTransaction = _makeTransaction;
    }

    public void setGetSystemState(JButton _getSystemState) {
        this._getSystemState = _getSystemState;
    }

    public void setIncreaseTimeForOneHour(JButton _increaseTimeForOneHour) {
        this._increaseTimeForOneHour = _increaseTimeForOneHour;
    }
    public JButton getAddBank() {
        return _addBank;
    }

    public JButton getAddClient() {
        return _addClient;
    }

    public JButton getGetConfig() {
        return _getConfig;
    }

    public JButton getMakeTransaction() {
        return _makeTransaction;
    }

    public JButton getGetSystemState() {
        return _getSystemState;
    }

    public JButton getIncreaseTimeForOneHour() {
        return _increaseTimeForOneHour;
    }

    public JTextArea getTextArea() {
        return _textArea;
    }

    public void setTextArea(JTextArea _textArea) {
        this._textArea = _textArea;
    }

    public JLabel getInfo() {
        return _info;
    }

    public void setInfo(JLabel _info) {
        this._info = _info;
    }

    public JPanel getPanel() {
        return _panel;
    }

    public void setPanel(JPanel _panel) {
        this._panel = _panel;
    }

    public JButton getOk() {
        return Ok;
    }

    public void setOk(JButton ok) {
        Ok = ok;
    }

    /**
     * Window settings class constructor
     */

    public WindowSettings(ServiceView serviceView) {
        if (serviceView == null) throw new NullPointerException();
        _serviceView = serviceView;
    }

    /**
     * This method sets text into a _info area and set new button.
     * @param text
     * @param buttonToRemove
     */

    public void setNewParameter(String text, JButton buttonToRemove) {
        removeButton(buttonToRemove);
        _textArea.setText("");
        _info.setText(text);
        Ok = new JButton("OK");
        _panel.add(Ok, BorderLayout.SOUTH);
    }

    public void removeJTextArea(JTextArea area) {
        _serviceView.remove(area);
    }

    /**
     * sets transaction menu view
     */

    public void setTransactionMenu() {
        _additionalPanel = new JPanel();
        _additionalPanel2 = new JPanel();
        _additionalText = new JTextArea();
        _additionalText2 = new JTextArea();
        _scrollPane = new JScrollPane(_additionalText2);

        _additionalPanel.add(_additionalText);
        _additionalPanel2.add(_scrollPane);

        _serviceView.add(_additionalPanel, BorderLayout.WEST);
        _serviceView.add(_scrollPane, BorderLayout.EAST);

//        Ok = new JButton("OK");
//        _panel.add(Ok, BorderLayout.SOUTH);

        _serviceView.revalidate();
        _serviceView.repaint();
    }

    /**
     * Clear transaction menu view
     */

    public void clearTransactionMenu() {
        _additionalPanel.remove(_additionalText);
        _scrollPane.removeAll();
        _additionalPanel2.remove(_scrollPane);
        _serviceView.remove(_additionalPanel);
        _serviceView.remove(_additionalPanel2);
        _serviceView.remove(_scrollPane);
        _serviceView.add(_textArea, BorderLayout.CENTER);
        _serviceView.revalidate();
        _serviceView.repaint();
    }

    /**
     * This method sets text into a _info area and remove stock buttons view
     * @param text
     */

    public void setNewParameter(String text) {
        removeStockButtons();
        _info.setText(text);
        Ok = new JButton("OK");
        _panel.add(Ok, BorderLayout.SOUTH);
    }

    /**
     * Sets stock service view
     */

    void setStockView() {
        _serviceView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        _serviceView.setSize(1000, 400);

        _info.setText("Lets start");
        _textArea.setText("");
    }

    /**
     * Remove stock service buttons
     */

    void removeStockButtons() {
        _panel.remove(_addBank);
        _panel.remove(_addClient);
        _panel.remove(_getConfig);
        _panel.remove(_getSystemState);
        _panel.remove(_increaseTimeForOneHour);
        _panel.remove(_makeTransaction);
        _serviceView.revalidate();
        _serviceView.repaint();
    }

    /**
     * Remove certain button
     * @param button
     */

    void removeButton(JButton button) {
        _panel.remove(button);
        _serviceView.revalidate();
        _serviceView.repaint();
    }

    /**
     * Sets stock buttons
     */

    void setStockButtons() {
        _panel.add(_addBank, BorderLayout.CENTER);
        _panel.add(_addClient, BorderLayout.CENTER);
        _panel.add(_getConfig, BorderLayout.CENTER);
        _panel.add(_getSystemState, BorderLayout.CENTER);
        _panel.add(_makeTransaction, BorderLayout.CENTER);
        _panel.add(_increaseTimeForOneHour, BorderLayout.CENTER);
        _serviceView.revalidate();
        _serviceView.repaint();
    }

}
