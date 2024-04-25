package Database.Update;

import Database.Models.Address.Address;
import Database.Models.Division.Employee_Division;
import Database.Models.Employee.General_Employee;
import Database.Models.JobTitle.Employee_Job_Title;
import Database.Models.Payroll.FullTime_Payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class UpdateFTEmployeeDataById {
    public static HashMap<String, String> updateFTEmployeeDataById(int empId, General_Employee employee, Address address, FullTime_Payroll payroll,
                                                                   Employee_Division division, Employee_Job_Title jobTitle,
                                                                   String DATABASE_URL, String DATABASE_USER, String DATABASE_PASSWORD) {

        HashMap<String, String> response = new HashMap<>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            connection.setAutoCommit(false);

            // Update employees table
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE employees SET Fname=?, Lname=?, email=?, HireDate=?, Salary=?, SSN=? WHERE empid=?")) {
                statement.setString(1, employee.firstName);
                statement.setString(2, employee.lastName);
                statement.setString(3, employee.email);
                statement.setDate(4, employee.hireDate);
                statement.setDouble(5, employee.salary);
                statement.setString(6, employee.ssn);
                statement.setInt(7, empId);
                statement.executeUpdate();
            }

            // Update address table
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE address SET gender=?, pronouns=?, identified_race=?, DOB=?, phone=?, cityid=?, stateid=? WHERE empid=?")) {
                statement.setString(1, address.gender);
                statement.setString(2, address.pronouns);
                statement.setString(3, address.identifiedRace);
                statement.setDate(4, address.dob);
                statement.setString(5, address.phone);
                statement.setInt(6, address.cityId);
                statement.setInt(7, address.stateId);
                statement.setInt(8, empId);
                statement.executeUpdate();
            }

            // Update employee_division table
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE employee_division SET div_ID=? WHERE empid=?")) {
                statement.setInt(1, division.div_ID);
                statement.setInt(2, empId);
                statement.executeUpdate();
            }

            // Update employee_job_titles table
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE employee_job_titles SET job_title_id=? WHERE empid=?")) {
                statement.setInt(1, jobTitle.job_title_id);
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

            response.put("message", "Employee data updated successfully.");

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
