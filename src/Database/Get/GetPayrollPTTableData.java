package Database.Get;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GetPayrollPTTableData {
    public static ArrayList<HashMap<String, Object>> getPayrollPTTableData(String DATABASE_URL,
                                                                         String DATABASE_USER, String DATABASE_PASSWORD) {
        ArrayList<HashMap<String, Object>> dataQuery = new ArrayList<>();

        String sqlStatement = "SELECT e.empid, e.Fname, e.Lname, p.payID, p.pay_date, p.earnings, p.fed_tax, p.fed_med, p.fed_SS, " +
                "p.state_tax, p.hourly_wage " +
                "FROM employees e JOIN payroll_pt p ON e.empid = p.empid ";

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("empid", resultSet.getInt("empid"));
                dataMap.put("firstname", resultSet.getString("Fname"));
                dataMap.put("lastname", resultSet.getString("Lname"));
                dataMap.put("payid", resultSet.getString("payID"));
                dataMap.put("payDate", resultSet.getDate("pay_date"));
                dataMap.put("earnings", resultSet.getDouble("earnings"));
                dataMap.put("fedTax", resultSet.getDouble("fed_tax"));
                dataMap.put("fedMed", resultSet.getDouble("fed_med"));
                dataMap.put("fedSS", resultSet.getDouble("fed_SS"));
                dataMap.put("stateTax", resultSet.getDouble("state_tax"));
                dataMap.put("hourlyWage", resultSet.getDouble("hourly_wage"));

                dataQuery.add(dataMap);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return dataQuery;
    }
}
