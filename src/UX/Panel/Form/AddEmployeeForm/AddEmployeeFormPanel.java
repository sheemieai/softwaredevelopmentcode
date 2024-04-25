package UX.Panel.Form.AddEmployeeForm;

import static Database.Connection.Connection.getConnectionVariables;

import static Database.Add.InsertFTEmployeeData.insertFTEmployeeData;
import static Database.Add.InsertPTEmployeeData.insertPTEmployeeData;
import Database.Models.Address.Address;
import Database.Models.Division.Employee_Division;
import Database.Models.Employee.PartTime_Employee;
import Database.Models.Employee.FullTime_Employee;
import Database.Models.JobTitle.Employee_Job_Title;
import Database.Models.Payroll.FullTime_Payroll;
import Database.Models.Payroll.PartTime_Payroll;
import UX.Button.MainPanelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AddEmployeeFormPanel extends JPanel {
    private String changeButtonTrigger = "fullTime";
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField hireDateField;
    private JTextField salaryField;
    private JTextField ssnField;
    private JTextField genderField;
    private JTextField pronounsField;
    private JTextField identifyRaceField;
    private JTextField dobField;
    private JTextField phoneNumberField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField divisionField;
    private JTextField jobTitleField;

    public AddEmployeeFormPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set the initial grid bag layout constraints
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // First Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("First Name:"), gbc);

        firstNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(firstNameField, gbc);

        // Last Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Last Name:"), gbc);

        lastNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(lastNameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Email (email@email.com):"), gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(emailField, gbc);

        // Hire Date
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Hire Date (yyyy-mm-dd):"), gbc);

        hireDateField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(hireDateField, gbc);

        // Salary
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Salary:"), gbc);

        salaryField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(salaryField, gbc);

        // Gender
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Gender:"), gbc);

        genderField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(genderField, gbc);

        // Pronouns
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Pronouns:"), gbc);

        pronounsField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(pronounsField, gbc);

        // Identified Race
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Identified Race:"), gbc);

        identifyRaceField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(identifyRaceField, gbc);

        // DOB
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(new JLabel("Date of Birth (yyyy-mm-dd):"), gbc);

        dobField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 10;
        add(dobField, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 11;
        add(new JLabel("Phone number (###-###-####):"), gbc);

        phoneNumberField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 11;
        add(phoneNumberField, gbc);

        // City
        gbc.gridx = 0;
        gbc.gridy = 12;
        add(new JLabel("City:"), gbc);

        cityField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 12;
        add(cityField, gbc);

        // State
        gbc.gridx = 0;
        gbc.gridy = 13;
        add(new JLabel("State:"), gbc);

        stateField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 13;
        add(stateField, gbc);

        // Employee's Division
        gbc.gridx = 0;
        gbc.gridy = 14;
        add(new JLabel("Employee's Job Division:"), gbc);

        divisionField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 14;
        add(divisionField, gbc);

        // Employee's Job Title
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(new JLabel("Employee's Job Title:"), gbc);

        jobTitleField = new JTextField(20);
        gbc.gridx = 4;
        gbc.gridy = 0;
        add(jobTitleField, gbc);

        // Employee's SSN
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(new JLabel("Employee's SSN:"), gbc);

        ssnField = new JTextField(20);
        gbc.gridx = 4;
        gbc.gridy = 1;
        add(ssnField, gbc);

        // Employee Type
        gbc.gridx = 3;
        gbc.gridy = 11;
        add(new JLabel("Employee Form Type: "), gbc);

        JLabel alertLabel = new JLabel("");
        gbc.gridx = 4;
        gbc.gridy = 10;
        add(alertLabel, gbc);

        JLabel employeeTypeLabel = new JLabel("Full Time Employee Form");
        gbc.gridx = 4;
        gbc.gridy = 11;
        add(employeeTypeLabel, gbc);

        MainPanelButton employeeFTButton = new MainPanelButton("Full Time");
        gbc.gridx = 4;
        gbc.gridy = 12;
        add(employeeFTButton, gbc);

        MainPanelButton employeePTButton = new MainPanelButton("Part Time");
        gbc.gridx = 4;
        gbc.gridy = 13;
        add(employeePTButton, gbc);

        MainPanelButton submitButton = new MainPanelButton("Submit");
        gbc.gridx = 4;
        gbc.gridy = 14;
        add(submitButton, gbc);

        employeePTButton.addActionListener(e -> {
            changeButtonTrigger = "partTime";
            employeeTypeLabel.setText("Part Time Employee Form");
        });

        employeeFTButton.addActionListener(e -> {
            changeButtonTrigger = "fullTime";
            employeeTypeLabel.setText("Full Time Employee Form");
        });

        Timer timer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alertLabel.setText("");
            }
        });

        submitButton.addActionListener(e -> {
            final HashMap<String, String> connectionVariables = getConnectionVariables();
            final String url = connectionVariables.get("url");
            final String user = connectionVariables.get("user");
            final String pw = connectionVariables.get("pw");

            if (changeButtonTrigger.equals("partTime")) {
                PartTime_Employee employeeData = getPTEmployeeFromForm();
                Address addressData = getAddressFromForm();
                PartTime_Payroll payrollData = getPayrollPTFromForm();
                Employee_Division divisionData = getDivisionFromForm();
                Employee_Job_Title jobTitleData = getJobTitleFromForm();

                System.out.println(employeeData);

                HashMap<String, String> response = insertPTEmployeeData(employeeData, addressData, payrollData, divisionData, jobTitleData, url, user, pw);
                if (response == null) {
                    alertLabel.setText("Error: Insert did not run.");
                } else {
                    alertLabel.setText(response.get("message"));
                }

                timer.setRepeats(false);
                timer.start();
            } else {
                FullTime_Employee employeeData = getFTEmployeeFromForm();
                Address addressData = getAddressFromForm();
                FullTime_Payroll payrollData = getPayrollFTFromForm();
                Employee_Division divisionData = getDivisionFromForm();
                Employee_Job_Title jobTitleData = getJobTitleFromForm();
                HashMap<String, String> response = insertFTEmployeeData(employeeData, addressData, payrollData, divisionData, jobTitleData, url, user, pw);
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

    private PartTime_Employee getPTEmployeeFromForm() {
        return PartTime_Employee.setFromTextFields(firstNameField, lastNameField, emailField, hireDateField, salaryField, ssnField);
    }

    private FullTime_Employee getFTEmployeeFromForm() {
        return FullTime_Employee.setFromTextFields(firstNameField, lastNameField, emailField, hireDateField, salaryField, ssnField);
    }

    private Address getAddressFromForm() {
        return Address.setFromTextFields(genderField, pronounsField, identifyRaceField, dobField, phoneNumberField, cityField, stateField);
    }

    private Employee_Division getDivisionFromForm() {
        return Employee_Division.setFromTextFields(divisionField);
    }

    private Employee_Job_Title getJobTitleFromForm() {
        return Employee_Job_Title.setFromTextFields(jobTitleField);
    }

    private PartTime_Payroll getPayrollPTFromForm() {
        return PartTime_Payroll.setFromTextFields(salaryField);
    }

    private FullTime_Payroll getPayrollFTFromForm() {
        return FullTime_Payroll.setFromTextFields(salaryField);
    }
}

