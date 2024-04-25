package Database.Models.Employee;

import javax.swing.*;
import java.sql.Date;

public class General_Employee implements Employee {
    public String firstName;
    public String lastName;
    public String email;
    public Date hireDate;
    public double salary;
    public String ssn;

    public General_Employee(String firstName, String lastName, String email, Date hireDate, double salary, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.salary = salary;
        this.ssn = ssn;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Date getHireDate() {
        return hireDate;
    }

    @Override
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String getSSN() {
        return ssn;
    }

    @Override
    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public static General_Employee setFromTextFields(JTextField firstNameField, JTextField lastNameField, JTextField emailField,
                                                     JTextField hireDateField, JTextField salaryField, JTextField ssnField) {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            Date hireDate = Date.valueOf(hireDateField.getText());
            double salary = Double.parseDouble(salaryField.getText());
            String ssn = ssnField.getText();
            return new General_Employee(firstName, lastName, email, hireDate, salary, ssn);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
