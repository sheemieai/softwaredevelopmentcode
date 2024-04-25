package Database.Search;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.Search.SearchEmployeeById.searchEmployeeById;

public class SearchEmployeeByName {
    public static ArrayList<HashMap<String, Object>> searchEmployeeByName(String firstName, String lastName, String DATABASE_URL,
                                                                   String DATABASE_USER, String DATABASE_PASSWORD) {
        ArrayList<HashMap<String, Object>> dataQuery = new ArrayList<>();

        String sqlStatement = "SELECT e.empid FROM employees e WHERE e.Fname = ? AND e.Lname = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                ArrayList<HashMap<String, Object>> dataArray = searchEmployeeById(resultSet.getInt("empid"),
                        DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

                if (!dataArray.isEmpty()) {
                    dataQuery.add(dataArray.get(0));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return dataQuery;
    }
}
