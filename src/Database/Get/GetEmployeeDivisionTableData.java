package Database.Get;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GetEmployeeDivisionTableData {
    public static ArrayList<HashMap<String, Object>> getEmployeeDivisionTableData(String DATABASE_URL,
                                                                   String DATABASE_USER, String DATABASE_PASSWORD) {
        ArrayList<HashMap<String, Object>> dataQuery = new ArrayList<>();

        String sqlStatement = "SELECT e.empid, e.Fname, e.Lname, d.Name " +
                "FROM employees e " +
                "JOIN employee_division ed ON e.empid = ed.empid " +
                "JOIN division d ON ed.div_ID = d.ID";

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("empid", resultSet.getInt("empid"));
                dataMap.put("firstname", resultSet.getString("Fname"));
                dataMap.put("lastname", resultSet.getString("Lname"));
                dataMap.put("division", resultSet.getString("Name"));

                dataQuery.add(dataMap);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return dataQuery;
    }
}
