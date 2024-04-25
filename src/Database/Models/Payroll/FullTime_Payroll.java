package Database.Models.Payroll;

import javax.swing.*;
import java.sql.Date;

public class FullTime_Payroll extends General_Payroll {
    public double retire401k;
    public double healthCare;

    public FullTime_Payroll(Date payDate, double salary) {
        super(payDate, salary);
        this.retire401k = calculateRetire401k(salary);
        this.healthCare = calculateHealthCare(salary);
    }

    private double calculateRetire401k(double salary) {
        return (salary / 52.0) * 0.004;
    }

    private double calculateHealthCare(double salary) {
        return (salary / 52.0) * 0.031;
    }

    public double getRetire401k() {
        return retire401k;
    }

    public void setRetire401k(double retire401k) {
        this.retire401k = retire401k;
    }

    public double getHealthCare() {
        return healthCare;
    }

    public void setHealthCare(double healthCare) {
        this.healthCare = healthCare;
    }

    public static FullTime_Payroll setFromTextFields(JTextField salaryField) {
        try {
            double salary = Double.parseDouble(salaryField.getText());
            Date payDate = Date.valueOf("2023-11-30");

            return new FullTime_Payroll(payDate, salary);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}