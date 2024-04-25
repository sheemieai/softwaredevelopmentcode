package Database.Models.Payroll;

import javax.swing.*;
import java.sql.Date;

public class PartTime_Payroll extends General_Payroll {
    public double hourlyWage = -1;

    public PartTime_Payroll(Date payDate, double salary) {
        super(payDate, salary);
        this.hourlyWage = calculateHourlyWage(salary);
    }

    private double calculateHourlyWage(double salary) {
        return (salary / 52.0) / 25.0;
    }
    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public static PartTime_Payroll setFromTextFields(JTextField salaryField) {
        try {
            double salary = Double.parseDouble(salaryField.getText());
            Date payDate = Date.valueOf("2023-12-1");

            return new PartTime_Payroll(payDate, salary);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
