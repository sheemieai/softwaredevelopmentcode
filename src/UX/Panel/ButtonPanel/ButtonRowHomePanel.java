package UX.Panel.ButtonPanel;

import UX.Button.MainPanelButton;

import javax.swing.*;
import java.awt.*;

public class ButtonRowHomePanel extends JPanel {
    private MainPanelButton employeeTableButton;
    private MainPanelButton addressTableButton;
    private MainPanelButton employeeDivisionTableButton;
    private MainPanelButton employeeJobTitleTableButton;
    private MainPanelButton payRollTableButton;
    private MainPanelButton payRollPTTableButton;

    private MainPanelButton refreshButton;

    public ButtonRowHomePanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        employeeTableButton = new MainPanelButton("Employee Table");
        addressTableButton = new MainPanelButton("Address Table");
        employeeDivisionTableButton = new MainPanelButton("Employee Division Table");
        employeeJobTitleTableButton = new MainPanelButton("Employee Job Title Table");
        payRollTableButton = new MainPanelButton("Pay Roll FT Table");
        payRollPTTableButton = new MainPanelButton("Pay Roll PT Table");
        refreshButton = new MainPanelButton("Refresh Tables");

        add(employeeTableButton);
        add(addressTableButton);
        add(employeeDivisionTableButton);
        add(employeeJobTitleTableButton);
        add(payRollTableButton);
        add(payRollPTTableButton);
        add(refreshButton);
    }

    public JButton getEmployeeTableButton() {
        return employeeTableButton;
    }

    public JButton getAddressTableButton() {
        return addressTableButton;
    }

    public JButton getEmployeeDivisionTableButton() {
        return employeeDivisionTableButton;
    }

    public JButton getEmployeeJobTitleTableButton() {
        return employeeJobTitleTableButton;
    }

    public JButton getPayRollTableButton() {
        return payRollTableButton;
    }
    public JButton getPayRollPTTableButton() {
        return payRollPTTableButton;
    }
    public JButton getRefreshButton() {
        return refreshButton;
    }
}


