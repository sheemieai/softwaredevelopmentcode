package Database.Get;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GetAddressTableData {
    public static ArrayList<HashMap<String, Object>> getAddressTableData(String DATABASE_URL,
                                                                   String DATABASE_USER, String DATABASE_PASSWORD) {
        ArrayList<HashMap<String, Object>> dataQuery = new ArrayList<>();

        String sqlStatement = "SELECT e.empid, e.Fname, e.Lname, a.gender, a.pronouns, a.identified_race, " +
                "a.DOB, a.phone, c.cityname, s.statename " +
                "FROM employees e " +
                "JOIN address a ON e.empid = a.empid " +
                "JOIN city c ON a.cityid = c.cityid " +
                "JOIN state s ON a.stateid = s.stateid ";

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("empid", resultSet.getInt("empid"));
                dataMap.put("firstname", resultSet.getString("Fname"));
                dataMap.put("lastname", resultSet.getString("Lname"));
                dataMap.put("gender", resultSet.getString("gender"));
                dataMap.put("pronouns", resultSet.getString("pronouns"));
                dataMap.put("identifiedRace", resultSet.getString("identified_race"));
                dataMap.put("dob", resultSet.getDate("DOB"));
                dataMap.put("phone", resultSet.getString("phone"));
                dataMap.put("city", resultSet.getString("cityname"));
                dataMap.put("state", resultSet.getString("statename"));

                dataQuery.add(dataMap);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return dataQuery;
    }
}
