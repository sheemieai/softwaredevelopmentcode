package Database.Models.Employee;

import javax.swing.*;
import java.sql.Date;

public class PartTime_Employee extends General_Employee {
    private double hourlyWage;

    public PartTime_Employee(String firstName, String lastName, String email, Date hireDate, double salary, String ssn) {
        super(firstName, lastName, email, hireDate, salary, ssn);
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public static PartTime_Employee setFromTextFields(JTextField firstNameField, JTextField lastNameField, JTextField emailField,
                                                     JTextField hireDateField, JTextField salaryField, JTextField ssnField) {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            Date hireDate = Date.valueOf(hireDateField.getText());
            int salary = Integer.parseInt(salaryField.getText());
            String ssn = ssnField.getText();
            return new PartTime_Employee(firstName, lastName, email, hireDate, salary, ssn);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

