package Database.Get;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GetEmployeeJobTitleTableData {
    public static ArrayList<HashMap<String, Object>> getEmployeeJobTitleTableData(String DATABASE_URL,
                                                                           String DATABASE_USER, String DATABASE_PASSWORD) {
        ArrayList<HashMap<String, Object>> dataQuery = new ArrayList<>();

        String sqlStatement = "SELECT e.empid, e.Fname, e.Lname, j.job_title " +
                "FROM employees e " +
                "JOIN employee_job_titles ej ON e.empid = ej.empid " +
                "JOIN job_titles j ON ej.job_title_id = j.job_title_id";

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("empid", resultSet.getInt("empid"));
                dataMap.put("firstname", resultSet.getString("Fname"));
                dataMap.put("lastname", resultSet.getString("Lname"));
                dataMap.put("jobTitle", resultSet.getString("job_title"));

                dataQuery.add(dataMap);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return dataQuery;
    }
}
