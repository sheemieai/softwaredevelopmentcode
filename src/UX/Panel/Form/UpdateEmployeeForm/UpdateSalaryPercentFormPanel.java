package UX.Panel.Form.UpdateEmployeeForm;

import Database.Get.GetEmployeeTableData;
import Database.Get.GetPayrollPTTableData;
import Database.Models.Employee.FullTime_Employee;
import Database.Models.Employee.PartTime_Employee;
import Database.Models.Payroll.FullTime_Payroll;
import Database.Models.Payroll.PartTime_Payroll;
import UX.Button.MainPanelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Update.UpdateFTEmployeeSalary.updateFTEmployeeSalary;
import static Database.Update.UpdatePTEmployeeSalary.updatePTEmployeeSalary;

public class UpdateSalaryPercentFormPanel extends JPanel {
    private final HashMap<String, String> connectionVariables;
    String firstName;
    String lastName;
    String email;
    Date hireDate;
    double salary;
    int updatedSalary;
    String ssn;
    private PartTime_Employee employeePTData;
    private FullTime_Employee employeeFTData;
    private PartTime_Payroll payrollPTFormData;
    private FullTime_Payroll payrollFTFormData;
    private JTextField employeeIdField;
    private JTextField updateSalaryPercentField;
    private JTextField updatedSalaryField;

    public UpdateSalaryPercentFormPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        updatedSalaryField = new JTextField();

        // Set the initial grid bag layout constraints
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Employee Id
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Employee Id:"), gbc);

        employeeIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(employeeIdField, gbc);

        // Update Salary by Percent
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Update salary by this percent:"), gbc);

        updateSalaryPercentField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(updateSalaryPercentField, gbc);

        MainPanelButton submitButton = new MainPanelButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(submitButton, gbc);

        JLabel alertLabel = new JLabel("");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(alertLabel, gbc);

        connectionVariables = getConnectionVariables();
        final String url = connectionVariables.get("url");
        final String user = connectionVariables.get("user");
        final String pw = connectionVariables.get("pw");

        Timer timer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alertLabel.setText("");
            }
        });

        submitButton.addActionListener(e -> {
            int empid = Integer.parseInt(employeeIdField.getText());

            ArrayList<HashMap<String,Object>> employeeData = GetEmployeeTableData.getEmployeeTableData(url,
                    user, pw);
            ArrayList<HashMap<String,Object>> payrollPTData = GetPayrollPTTableData.getPayrollPTTableData(url,
                    user, pw);

            HashMap<String, Object> employeeMap = searchForEmployeeData(empid, employeeData);

            firstName = (String) employeeMap.get("firstname");
            lastName = (String) employeeMap.get("lastname");
            email = (String) employeeMap.get("email");
            hireDate = (Date) employeeMap.get("hireDate");

            salary = (Double) employeeMap.get("salary");
            double percent = Double.parseDouble(updateSalaryPercentField.getText()) / 100;
            updatedSalary = (int) (salary * (1 + percent));
            updatedSalaryField.setText(String.valueOf(updatedSalary));

            ssn = (String) employeeMap.get("ssn");

            if (searchForEmployeeId(empid, payrollPTData)) {
                employeePTData = getPTEmployeeFromForm();
                payrollPTFormData = getPayrollPTFromForm();

                HashMap<String, String> response = updatePTEmployeeSalary(empid, employeePTData,
                        payrollPTFormData, url, user, pw);
                System.out.println(response);
                if (response == null) {
                    alertLabel.setText("Error: Insert did not run.");
                } else {
                    alertLabel.setText(response.get("message"));
                }

                timer.setRepeats(false);
                timer.start();
            } else {
                employeeFTData = getFTEmployeeFromForm();
                payrollFTFormData = getPayrollFTFromForm();

                HashMap<String, String> response = updateFTEmployeeSalary(empid, employeeFTData,
                        payrollFTFormData, url, user, pw);
                if (response == null) {
                    alertLabel.setText("Error: Insert did not run.");
                } else {
                    alertLabel.setText(response.get("message"));
                }

                timer.setRepeats(false);
                timer.start();
            }
        });
    }

    private FullTime_Employee getFTEmployeeFromForm() {
        try {
            return new FullTime_Employee(firstName, lastName, email, hireDate, updatedSalary, ssn);
        } catch (Exception e) {
            System.err.println("Failed to create FullTime_Employee: " + e);
            return null;
        }
    }

    private PartTime_Employee getPTEmployeeFromForm() {
        try {
            return new PartTime_Employee(firstName, lastName, email, hireDate, updatedSalary, ssn);
        } catch (Exception e) {
            System.err.println("Failed to create PartTime_Employee: " + e);
            return null;
        }
    }

    private PartTime_Payroll getPayrollPTFromForm() {
        return PartTime_Payroll.setFromTextFields(updatedSalaryField);
    }

    private FullTime_Payroll getPayrollFTFromForm() {
        return FullTime_Payroll.setFromTextFields(updatedSalaryField);
    }

    private HashMap<String,Object> searchForEmployeeData(int empid, ArrayList<HashMap<String,Object>> arrayData) {
        for (HashMap<String, Object> data : arrayData) {
            Object value = data.get("empid");

            if (value instanceof Integer) {
                int employeeId = (Integer) value;
                if (employeeId == empid) {
                    return data;
                }
            }
        }

        return null;
    }

    private boolean searchForEmployeeId(int empid, ArrayList<HashMap<String,Object>> arrayData) {
        for (HashMap<String, Object> data : arrayData) {
            Object value = data.get("empid");

            if (value instanceof Integer) {
                int employeeId = (Integer) value;
                if (employeeId == empid) {
                    return true;
                }
            }
        }

        return false;
    }
}

