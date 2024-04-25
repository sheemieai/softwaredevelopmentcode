package Database.Get;

import java.util.ArrayList;
import java.util.HashMap;

import java.sql.*;

public class GetEmployeeTableData {
    public static ArrayList<HashMap<String, Object>> getEmployeeTableData(String DATABASE_URL,
                                                                   String DATABASE_USER, String DATABASE_PASSWORD) {
        ArrayList<HashMap<String, Object>> dataQuery = new ArrayList<>();

        String sqlStatement = "SELECT e.empid, e.Fname, e.Lname, e.email, e.HireDate, e.Salary, e.SSN " +
                "FROM employees e";

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("empid", resultSet.getInt("empid"));
                dataMap.put("firstname", resultSet.getString("Fname"));
                dataMap.put("lastname", resultSet.getString("Lname"));
                dataMap.put("email", resultSet.getString("email"));
                dataMap.put("hireDate", resultSet.getDate("HireDate"));
                dataMap.put("salary", resultSet.getDouble("Salary"));
                dataMap.put("ssn", resultSet.getString("SSN"));

                dataQuery.add(dataMap);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return dataQuery;
    }
}
