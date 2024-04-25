package Database.Models.Employee;

import javax.swing.*;
import java.sql.Date;

public class FullTime_Employee extends General_Employee {
    private double retire401k;
    private double healthCare;
    private double fedMed;
    public final double salary = getSalary();

    public FullTime_Employee(String firstName, String lastName, String email, Date hireDate, int salary, String ssn) {
        super(firstName, lastName, email, hireDate, salary, ssn);
    }

    public double getRetire401k() {
        retire401k = (salary / 52.0) * 0.004;
        return retire401k;
    }

    public void setRetire401k(double retire401k) {
        this.retire401k = retire401k;
    }

    public double getHealthCare() {
        healthCare = (salary / 52.0) * 0.031;
        return healthCare;
    }

    public void setHealthCare(double healthCare) {
        this.healthCare = healthCare;
    }

    public double getFedMed() {
        fedMed = (salary / 52.0) * 0.0145;
        return fedMed;
    }

    public void setFedMed(double fedMed) {
        this.fedMed = fedMed;
    }

    public static FullTime_Employee setFromTextFields(JTextField firstNameField, JTextField lastNameField, JTextField emailField,
                                                      JTextField hireDateField, JTextField salaryField, JTextField ssnField) {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            Date hireDate = Date.valueOf(hireDateField.getText());
            int salary = Integer.parseInt(salaryField.getText());
            String ssn = ssnField.getText();
            return new FullTime_Employee(firstName, lastName, email, hireDate, salary, ssn);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

