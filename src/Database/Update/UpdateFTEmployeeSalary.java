package Database.Update;

import Database.Models.Employee.General_Employee;
import Database.Models.Payroll.FullTime_Payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class UpdateFTEmployeeSalary {
    public static HashMap<String, String> updateFTEmployeeSalary(int empId, General_Employee employee, FullTime_Payroll payroll,
                                                                 String DATABASE_URL, String DATABASE_USER, String DATABASE_PASSWORD) {
        HashMap<String, String> response = new HashMap<>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            connection.setAutoCommit(false);

            // Update employees table
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE employees SET Salary=? WHERE empid=?")) {
                statement.setDouble(1, employee.salary);
                statement.setInt(2, empId);
                statement.executeUpdate();
            }

            // Update payroll table
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE payroll SET pay_date=?, earnings=?, fed_tax=?, fed_med=?, fed_SS=?, state_tax=?, retire_401k=?, health_care=? WHERE empid=?")) {
                statement.setDate(1, payroll.payDate);
                statement.setDouble(2, payroll.earnings);
                statement.setDouble(3, payroll.fedTax);
                statement.setDouble(4, payroll.fedMed);
                statement.setDouble(5, payroll.fedSS);
                statement.setDouble(6, payroll.stateTax);
                statement.setDouble(7, payroll.retire401k);
                statement.setDouble(8, payroll.healthCare);
                statement.setInt(9, empId);
                statement.executeUpdate();
            }
            connection.commit();
            response.put("message", "Employee salary updated successfully.");

            return response;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    response.put("rollback", "Transaction rolled back due to an error.");
                } catch (SQLException ex) {
                    response.put("rollback_error", "Error during rollback: " + ex.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Non-SQL Error occurred: " + e.getMessage());
            response.put("message", "Non-SQL Error occurred.");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    response.put("close_error", "Error closing the connection: " + e.getMessage());
                }
            }
        }

        return response;
    }
}
