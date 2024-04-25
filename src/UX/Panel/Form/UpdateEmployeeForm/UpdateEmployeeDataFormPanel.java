package UX.Panel.Form.UpdateEmployeeForm;

import Database.Get.*;
import Database.Models.Address.Address;
import Database.Models.Division.Employee_Division;
import Database.Models.Employee.FullTime_Employee;
import Database.Models.Employee.PartTime_Employee;
import Database.Models.JobTitle.Employee_Job_Title;
import Database.Models.Payroll.FullTime_Payroll;
import Database.Models.Payroll.PartTime_Payroll;
import UX.Button.MainPanelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Update.UpdateFTEmployeeDataById.updateFTEmployeeDataById;
import static Database.Update.UpdatePTEmployeeDataById.updatePTEmployeeDataById;

public class UpdateEmployeeDataFormPanel extends JPanel {
    private PartTime_Employee employeePTFormData;
    private FullTime_Employee employeeFTFormData;
    private Address addressFormData;
    private PartTime_Payroll payrollPTFormData;
    private FullTime_Payroll payrollFTFormData;
    private Employee_Division divisionFormData;
    private Employee_Job_Title jobTitleFormData;
    private final HashMap<String, String> connectionVariables;
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
    private JTextField employeeIDField;
    private JLabel alertLabel;
    public UpdateEmployeeDataFormPanel() {
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
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(new JLabel("Employee's Job Division:"), gbc);

        divisionField = new JTextField(20);
        gbc.gridx = 4;
        gbc.gridy = 0;
        add(divisionField, gbc);

        // Employee's Job Title
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(new JLabel("Employee's Job Title:"), gbc);

        jobTitleField = new JTextField(20);
        gbc.gridx = 4;
        gbc.gridy = 1;
        add(jobTitleField, gbc);

        // Employee's SSN
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(new JLabel("Employee's SSN:"), gbc);

        ssnField = new JTextField(20);
        gbc.gridx = 4;
        gbc.gridy = 2;
        add(ssnField, gbc);

        // Employee's ID
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(new JLabel("Employee's ID:"), gbc);

        employeeIDField = new JTextField(20);
        gbc.gridx = 4;
        gbc.gridy = 3;
        add(employeeIDField, gbc);

        MainPanelButton searchButton = new MainPanelButton("Get Employee's Data");
        gbc.gridx = 4;
        gbc.gridy = 4;
        add(searchButton, gbc);

        alertLabel = new JLabel("");
        gbc.gridx = 4;
        gbc.gridy = 12;
        add(alertLabel, gbc);

        MainPanelButton submitButton = new MainPanelButton("Submit");
        gbc.gridx = 4;
        gbc.gridy = 13;
        add(submitButton, gbc);

        Timer timer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alertLabel.setText("");
            }
        });

        connectionVariables = getConnectionVariables();
        final String url = connectionVariables.get("url");
        final String user = connectionVariables.get("user");
        final String pw = connectionVariables.get("pw");

        searchButton.addActionListener(e -> {
            ArrayList<HashMap<String,Object>> addressData = GetAddressTableData.getAddressTableData(url, user, pw);
            ArrayList<HashMap<String,Object>> employeeData = GetEmployeeTableData.getEmployeeTableData(url, user, pw);
            ArrayList<HashMap<String,Object>> divisionData = GetEmployeeDivisionTableData.getEmployeeDivisionTableData(url, user, pw);
            ArrayList<HashMap<String,Object>> jobTitleData = GetEmployeeJobTitleTableData.getEmployeeJobTitleTableData(url, user, pw);

            int empid = Integer.parseInt(employeeIDField.getText());

            HashMap<String, Object> addressMap = searchForEmployeeData(empid, addressData);
            HashMap<String, Object> employeeMap = searchForEmployeeData(empid, employeeData);
            HashMap<String, Object> divisionMap = searchForEmployeeData(empid, divisionData);
            HashMap<String, Object> jobTitleMap = searchForEmployeeData(empid, jobTitleData);

            firstNameField.setText((String) employeeMap.get("firstname"));
            lastNameField.setText((String) employeeMap.get("lastname"));
            emailField.setText((String) employeeMap.get("email"));
            hireDateField.setText(convertDateToString(employeeMap.get("hireDate")));
            salaryField.setText(convertDoubleToString(employeeMap.get("salary")));
            genderField.setText((String) addressMap.get("gender"));
            pronounsField.setText((String) addressMap.get("pronouns"));
            identifyRaceField.setText((String) addressMap.get("identifiedRace"));
            dobField.setText(convertDateToString(addressMap.get("dob")));
            phoneNumberField.setText((String) addressMap.get("phone"));
            cityField.setText((String) addressMap.get("city"));
            stateField.setText((String) addressMap.get("state"));
            divisionField.setText((String) divisionMap.get("division"));
            jobTitleField.setText((String) jobTitleMap.get("jobTitle"));
            ssnField.setText((String) employeeMap.get("ssn"));
        });

        submitButton.addActionListener(e -> {
            int empid = Integer.parseInt(employeeIDField.getText());

            ArrayList<HashMap<String,Object>> payrollPTData = GetPayrollPTTableData.getPayrollPTTableData(url,
                    user, pw);

            addressFormData = getAddressFromForm();
            divisionFormData = getDivisionFromForm();
            jobTitleFormData = getJobTitleFromForm();

            if (searchForEmployeeId(empid, payrollPTData)) {
                employeePTFormData = getPTEmployeeFromForm();
                payrollPTFormData = getPayrollPTFromForm();

                HashMap<String, String> response = updatePTEmployeeDataById(empid, employeePTFormData,
                        addressFormData, payrollPTFormData, divisionFormData, jobTitleFormData, url, user, pw);
                System.out.println(response);
                if (response == null) {
                    alertLabel.setText("Error: Insert did not run.");
                } else {
                    alertLabel.setText(response.get("message"));
                }

                timer.setRepeats(false);
                timer.start();
            } else {
                employeeFTFormData = getFTEmployeeFromForm();
                payrollFTFormData = getPayrollFTFromForm();

                HashMap<String, String> response = updateFTEmployeeDataById(empid, employeeFTFormData,
                        addressFormData, payrollFTFormData, divisionFormData, jobTitleFormData, url, user, pw);
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

    private PartTime_Employee getPTEmployeeFromForm() {
        try {
            return new PartTime_Employee(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText(),
                    convertStringToDate(hireDateField.getText()),
                    Integer.parseInt(salaryField.getText()),
                    ssnField.getText()
            );
        } catch (Exception e) {
            System.err.println("Failed to create PartTime_Employee: " + e);
            return null;
        }
    }

    private FullTime_Employee getFTEmployeeFromForm() {
        try {
            return new FullTime_Employee(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText(),
                    convertStringToDate(hireDateField.getText()),
                    Integer.parseInt(salaryField.getText()),
                    ssnField.getText()
            );
        } catch (Exception e) {
            System.err.println("Failed to create FullTime_Employee: " + e);
            return null;
        }
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

    private Date convertStringToDate(String dateString) {
        try {
            LocalDate localDate = LocalDate.parse(dateString);
            return Date.valueOf(localDate);
        } catch (DateTimeParseException e) {
            System.err.println("Error converting string to SQL date: " + e.getMessage());
            return null;
        }
    }

    private String convertDateToString(Object dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateObject);
    }

    private String convertDoubleToString(Object doubleObject) {
        return String.format("%.0f", doubleObject);
    }
}
