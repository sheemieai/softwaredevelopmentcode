package UX.Panel.Home;

import UX.Panel.ButtonPanel.ButtonRowHomePanel;
import UX.Panel.Home.Panel.*;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private ButtonRowHomePanel buttonRowHomePanel;

    private HomeEmployeeTablePanel homeEmployeeTablePanel;
    private HomeAddressTablePanel homeAddressTablePanel;
    private HomeEmployeeDivisionTablePanel homeEmployeeDivisionTablePanel;
    private HomeEmployeeJobTitleTablePanel homeEmployeeJobTitleTablePanel;
    private HomePayrollTablePanel homePayrollTablePanel;
    private HomePayrollPTTablePanel homePayrollPTTablePanel;
    private JPanel contentPanel = new JPanel(new CardLayout());
    public HomePanel(int formTrigger) {
        super(new BorderLayout());
        buttonRowHomePanel = new ButtonRowHomePanel();
        add(buttonRowHomePanel, BorderLayout.NORTH);

        homeEmployeeTablePanel = new HomeEmployeeTablePanel();
        homeAddressTablePanel = new HomeAddressTablePanel();
        homeEmployeeDivisionTablePanel = new HomeEmployeeDivisionTablePanel();
        homeEmployeeJobTitleTablePanel = new HomeEmployeeJobTitleTablePanel();
        homePayrollTablePanel = new HomePayrollTablePanel();
        homePayrollPTTablePanel = new HomePayrollPTTablePanel();

        contentPanel.add(homeEmployeeTablePanel, "Employee");
        contentPanel.add(homeAddressTablePanel, "Address");
        contentPanel.add(homeEmployeeDivisionTablePanel, "Division");
        contentPanel.add(homeEmployeeJobTitleTablePanel, "JobTitle");
        contentPanel.add(homePayrollTablePanel, "Payroll");
        contentPanel.add(homePayrollPTTablePanel, "PayrollPT");
        add(contentPanel, BorderLayout.CENTER);

        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        switch(formTrigger) {
            case 1:
                cl.show(contentPanel, "Employee");
                break;
            case 2:
                cl.show(contentPanel, "Address");
                break;
            case 3:
                cl.show(contentPanel, "Division");
                break;
            case 4:
                cl.show(contentPanel, "JobTitle");
                break;
            case 5:
                cl.show(contentPanel, "Payroll");
                break;
            case 6:
                cl.show(contentPanel, "PayrollPT");
                break;
            default:
                cl.show(contentPanel, "Employee");
                break;
        }

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        JButton homeEmployeeTableButton = buttonRowHomePanel.getEmployeeTableButton();
        JButton homeAddressTableButton = buttonRowHomePanel.getAddressTableButton();
        JButton homeEmployeeDivisionTableButton = buttonRowHomePanel.getEmployeeDivisionTableButton();
        JButton homeEmployeeJobTitleTableButton = buttonRowHomePanel.getEmployeeJobTitleTableButton();
        JButton homePayrollTableButton = buttonRowHomePanel.getPayRollTableButton();
        JButton homePayrollPTTableButton = buttonRowHomePanel.getPayRollPTTableButton();
        JButton refreshButton = buttonRowHomePanel.getRefreshButton();

        homeEmployeeTableButton.addActionListener(e -> changeFormPanel("Employee"));
        homeAddressTableButton.addActionListener(e -> changeFormPanel("Address"));
        homeEmployeeDivisionTableButton.addActionListener(e -> changeFormPanel("Division"));
        homeEmployeeJobTitleTableButton.addActionListener(e -> changeFormPanel("JobTitle"));
        homePayrollTableButton.addActionListener(e -> changeFormPanel("Payroll"));
        homePayrollPTTableButton.addActionListener(e -> changeFormPanel("PayrollPT"));

        refreshButton.addActionListener(e -> refreshCurrentPanel());
    }

    private void changeFormPanel(String panelName) {
        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, panelName);
    }

    private void refreshCurrentPanel() {
        homeEmployeeTablePanel.refreshPanel();
        homeAddressTablePanel.refreshPanel();
        homeEmployeeDivisionTablePanel.refreshPanel();
        homeEmployeeJobTitleTablePanel.refreshPanel();
        homePayrollTablePanel.refreshPanel();
        homePayrollPTTablePanel.refreshPanel();

        initializePanels();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void initializePanels() {
        contentPanel.add(homeEmployeeTablePanel, "Employee");
        contentPanel.add(homeAddressTablePanel, "Address");
        contentPanel.add(homeEmployeeDivisionTablePanel, "Division");
        contentPanel.add(homeEmployeeJobTitleTablePanel, "JobTitle");
        contentPanel.add(homePayrollTablePanel, "Payroll");
        contentPanel.add(homePayrollPTTablePanel, "PayrollPT");
        add(contentPanel, BorderLayout.CENTER);

        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, "Employee");
    }
}

