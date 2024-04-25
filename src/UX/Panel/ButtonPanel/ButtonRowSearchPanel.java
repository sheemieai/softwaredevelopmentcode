package UX.Panel.ButtonPanel;

import UX.Button.MainPanelButton;

import javax.swing.*;
import java.awt.*;

public class ButtonRowSearchPanel extends JPanel {
    private MainPanelButton searchByEmployeeIdButton;
    private MainPanelButton searchByEmployeeNameButton;
    private MainPanelButton searchByEmployeeSSNButton;
    public ButtonRowSearchPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        searchByEmployeeIdButton = new MainPanelButton("Search by Employee ID");
        searchByEmployeeNameButton = new MainPanelButton("Search by Employee Name");
        searchByEmployeeSSNButton = new MainPanelButton("Search by Employee SSN");

        add(searchByEmployeeIdButton);
        add(searchByEmployeeNameButton);
        add(searchByEmployeeSSNButton);
    }

    public JButton getSearchByEmployeeIdButton() {
        return searchByEmployeeIdButton;
    }

    public JButton getSearchByEmployeeNameButton() {
        return searchByEmployeeNameButton;
    }

    public JButton getSearchByEmployeeSSNButton() {
        return searchByEmployeeSSNButton;
    }
}


