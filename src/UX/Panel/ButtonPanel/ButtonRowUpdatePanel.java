package UX.Panel.ButtonPanel;

import UX.Button.MainPanelButton;

import javax.swing.*;
import java.awt.*;

public class ButtonRowUpdatePanel extends JPanel {
    private MainPanelButton updateSalaryPercentButton;
    private MainPanelButton updateSalaryButton;
    private MainPanelButton updateEmployeeDataButton;
    public ButtonRowUpdatePanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        updateSalaryPercentButton = new MainPanelButton("Update Salary by Percent");
        updateSalaryButton = new MainPanelButton("Update Salary");
        updateEmployeeDataButton = new MainPanelButton("Update Employee Data");

        add(updateSalaryPercentButton);
        add(updateSalaryButton);
        add(updateEmployeeDataButton);
    }

    public JButton getUpdateSalaryPercentButton() {
        return updateSalaryPercentButton;
    }

    public JButton getUpdateSalaryButton() {
        return updateSalaryButton;
    }

    public JButton getUpdateEmployeeDataButton() {
        return updateEmployeeDataButton;
    }
}
