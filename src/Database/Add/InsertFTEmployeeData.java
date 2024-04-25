package Database.Add;

import Database.Models.Address.Address;
import Database.Models.Division.Employee_Division;
import Database.Models.Employee.General_Employee;
import Database.Models.JobTitle.Employee_Job_Title;
import Database.Models.Payroll.FullTime_Payroll;

import java.sql.*;
import java.util.HashMap;

public class InsertFTEmployeeData {
    public static HashMap<String, String> insertFTEmployeeData(General_Employee employee, Address address, FullTime_Payroll payroll,
                                                               Employee_Division division, Employee_Job_Title jobTitle,
                                                               String DATABASE_URL, String DATABASE_USER, String DATABASE_PASSWORD) {

        HashMap<String, String> response = new HashMap<>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            connection.setAutoCommit(false);

            int generatedEmpId = 0;

            String sqlStatement = "INSERT INTO employees (Fname, Lname, email, HireDate, Salary, SSN) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sqlStatement,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, employee.firstName);
                statement.setString(2, employee.lastName);
                statement.setString(3, employee.email);
                statement.setDate(4, employee.hireDate);
                statement.setDouble(5, employee.salary);
                statement.setString(6, employee.ssn);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating employee failed, no rows affected.");
                }

                //Get empid from Employees Table
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedEmpId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating employee failed, no ID obtained.");
                    }
                }
            }

            // Insert into address table
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO address (empid, gender, pronouns, identified_race, DOB, phone, cityid, stateid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                statement.setInt(1, generatedEmpId);
                statement.setString(2, address.gender);
                statement.setString(3, address.pronouns);
                statement.setString(4, address.identifiedRace);
                statement.setDate(5, address.dob);
                statement.setString(6, address.phone);
                statement.setInt(7, address.cityId);
                statement.setInt(8, address.stateId);
                statement.executeUpdate();
            }

            // Insert into employee_division table
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO employee_division (empid, div_ID) VALUES (?, ?)")) {
                statement.setInt(1, generatedEmpId);
                statement.setInt(2, division.div_ID);
                statement.executeUpdate();
            }

            // Insert into employee_job_titles table
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO employee_job_titles (empid, job_title_id) VALUES (?, ?)")) {
                statement.setInt(1, generatedEmpId);
                statement.setInt(2, jobTitle.job_title_id);
                statement.executeUpdate();
            }

            // Insert into payroll table
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO payroll (payID, pay_date, earnings, fed_tax, fed_med, fed_SS, state_tax, retire_401k, health_care, empid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                statement.setInt(1, generatedEmpId);
                statement.setDate(2, payroll.payDate);
                statement.setDouble(3, payroll.earnings);
                statement.setDouble(4, payroll.fedTax);
                statement.setDouble(5, payroll.fedMed);
                statement.setDouble(6, payroll.fedSS);
                statement.setDouble(7, payroll.stateTax);
                statement.setDouble(8, payroll.retire401k);
                statement.setDouble(9, payroll.healthCare);
                statement.setInt(10, generatedEmpId);
                statement.executeUpdate();
            }

            connection.commit();

            response.put("message", "Employee was added to database.");

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
