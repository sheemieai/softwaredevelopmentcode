package Database.Search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.Search.SearchEmployeeById.searchEmployeeById;

public class SearchEmployeeBySSN {
    public static ArrayList<HashMap<String, Object>> searchEmployeeBySSN(String ssn, String DATABASE_URL, String DATABASE_USER, String DATABASE_PASSWORD) {
        ArrayList<HashMap<String, Object>> dataQuery = new ArrayList<>();

        String sqlStatement = "SELECT e.empid FROM employees e WHERE SSN=?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, ssn);
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