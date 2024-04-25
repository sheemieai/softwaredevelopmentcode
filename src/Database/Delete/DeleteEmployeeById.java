package Database.Delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.HashMap;

public class DeleteEmployeeById {
    public static HashMap<String, String> deleteEmployeeById(int empId, String DATABASE_URL, String DATABASE_USER, String DATABASE_PASSWORD) throws SQLException {
        HashMap<String, String> response = new HashMap<>();
        int totalRowsDeleted = 0;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            connection.setAutoCommit(false);

            // Delete from address table
            totalRowsDeleted += executeDeleteStatement(connection, "DELETE FROM address WHERE empid = ?", empId);
            // Delete from employee_division table
            totalRowsDeleted += executeDeleteStatement(connection, "DELETE FROM employee_division WHERE empid = ?", empId);
            // Delete from employee_job_titles table
            totalRowsDeleted += executeDeleteStatement(connection, "DELETE FROM employee_job_titles WHERE empid = ?", empId);
            // Delete from payroll table
            totalRowsDeleted += executeDeleteStatement(connection, "DELETE FROM payroll WHERE empid = ?", empId);
            // Delete from payroll_pt table
            totalRowsDeleted += executeDeleteStatement(connection, "DELETE FROM payroll_pt WHERE empid = ?", empId);
            // Delete from employees table
            totalRowsDeleted += executeDeleteStatement(connection, "DELETE FROM employees WHERE empid = ?", empId);

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }

            System.out.println("SQL Error occurred: " + e.getMessage());
            response.put("message", "SQL Error Occurred.");
            return response;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

        if (totalRowsDeleted > 0) {
            response.put("message", "Employee was deleted.");
            return response;
        } else {
            response.put("message", "No employee found with ID " + empId);
            return response;
        }
    }

    private static int executeDeleteStatement(Connection connection, String sql, int empId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, empId);
            return statement.executeUpdate();
        }
    }
}
