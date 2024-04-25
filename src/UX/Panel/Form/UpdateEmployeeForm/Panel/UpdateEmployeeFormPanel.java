package UX.Panel.Form.UpdateEmployeeForm.Panel;

import UX.Panel.ButtonPanel.ButtonRowUpdatePanel;
import UX.Panel.Form.UpdateEmployeeForm.UpdateEmployeeSalaryFormPanel;
import UX.Panel.Form.UpdateEmployeeForm.UpdateSalaryPercentFormPanel;
import UX.Panel.Form.UpdateEmployeeForm.UpdateEmployeeDataFormPanel;

import javax.swing.*;
import java.awt.*;

public class UpdateEmployeeFormPanel extends JPanel {
    ButtonRowUpdatePanel buttonRowUpdatePanel;
    private JPanel contentPanel = new JPanel(new CardLayout());
    public UpdateEmployeeFormPanel(int formTrigger) {
        super(new BorderLayout());
        buttonRowUpdatePanel = new ButtonRowUpdatePanel();
        add(buttonRowUpdatePanel, BorderLayout.NORTH);

        UpdateEmployeeSalaryFormPanel updateEmployeeSalaryFormPanel = new UpdateEmployeeSalaryFormPanel();
        UpdateSalaryPercentFormPanel updateSalaryPercentFormPanel = new UpdateSalaryPercentFormPanel();
        UpdateEmployeeDataFormPanel updateEmployeeDataFormPanel = new UpdateEmployeeDataFormPanel();

        contentPanel.add(updateSalaryPercentFormPanel, "SalaryPercent");
        contentPanel.add(updateEmployeeSalaryFormPanel, "Salary");
        contentPanel.add(updateEmployeeDataFormPanel, "EmployeeData");
        add(contentPanel, BorderLayout.CENTER);

        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        switch(formTrigger) {
            case 1:
                cl.show(contentPanel, "SalaryPercent");
                break;
            case 2:
                cl.show(contentPanel, "Salary");
                break;
            case 3:
                cl.show(contentPanel, "EmployeeData");
                break;
            default:
                cl.show(contentPanel, "SalaryPercent");
                break;
        }

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        JButton updateSalaryPercentButton = buttonRowUpdatePanel.getUpdateSalaryPercentButton();
        JButton updateSalaryButton = buttonRowUpdatePanel.getUpdateSalaryButton();
        JButton updateEmployeeDataButton = buttonRowUpdatePanel.getUpdateEmployeeDataButton();
        updateSalaryPercentButton.addActionListener(e -> changeFormPanel("SalaryPercent"));
        updateSalaryButton.addActionListener(e -> changeFormPanel("Salary"));
        updateEmployeeDataButton.addActionListener(e -> changeFormPanel("EmployeeData"));
    }

    private void changeFormPanel(String panelName) {
        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, panelName);
    }
}

