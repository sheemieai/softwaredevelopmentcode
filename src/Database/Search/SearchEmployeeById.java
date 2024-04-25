package Database.Search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchEmployeeById {
    public static ArrayList<HashMap<String, Object>> searchEmployeeById(int empID, String DATABASE_URL, String DATABASE_USER, String DATABASE_PASSWORD) {
        ArrayList<HashMap<String, Object>> dataQuery = new ArrayList<>();
        String sqlStatement = "SELECT e.empid, e.Fname, e.Lname, e.email, e.HireDate, e.Salary, e.SSN, " +
                "a.gender, a.pronouns, a.identified_race, a.DOB, a.phone, c.cityname, s.statename, " +
                "pt.payID as pt_payid, pt.pay_date as pt_pay_date, pt.earnings as pt_earnings, pt.fed_tax as pt_fed_tax, " +
                "pt.fed_med as pt_fed_med, pt.fed_ss as pt_fed_ss, pt.state_tax as pt_state_tax, pt.hourly_wage as pt_hourly_wage, " +
                "p.payID as p_payid, p.pay_date as p_pay_date, p.earnings as p_earnings, p.fed_tax as p_fed_tax, " +
                "p.fed_med as p_fed_med, p.fed_ss as p_fed_ss, p.state_tax as p_state_tax, p.retire_401k as p_retire_401k, p.health_care as p_health_care, " +
                "j.job_title, d.Name " +
                "FROM employees e " +
                "LEFT JOIN address a ON e.empid = a.empid " +
                "LEFT JOIN payroll_pt pt ON e.empid = pt.empid " +
                "LEFT JOIN payroll p ON e.empid = p.empid " +
                "LEFT JOIN employee_job_titles ej ON e.empid = ej.empid " +
                "LEFT JOIN employee_division ed ON e.empid = ed.empid " +
                "LEFT JOIN city c ON a.cityid = c.cityid " +
                "LEFT JOIN state s ON a.stateid = s.stateid " +
                "LEFT JOIN job_titles j ON ej.job_title_id = j.job_title_id " +
                "LEFT JOIN division d ON ed.div_ID = d.ID " +
                "WHERE e.empid = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setInt(1, empID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dataQuery.add(extractEmployeeData(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

        return dataQuery;
    }

    private static HashMap<String, Object> extractEmployeeData(ResultSet resultSet) throws SQLException {
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("empid", resultSet.getInt("empid"));
        dataMap.put("firstname", resultSet.getString("Fname"));
        dataMap.put("lastname", resultSet.getString("Lname"));
        dataMap.put("email", resultSet.getString("email"));
        dataMap.put("hireDate", resultSet.getDate("HireDate"));
        dataMap.put("salary", resultSet.getDouble("Salary"));
        dataMap.put("ssn", resultSet.getString("SSN"));
        dataMap.put("gender", resultSet.getString("gender"));
        dataMap.put("pronouns", resultSet.getString("pronouns"));
        dataMap.put("identifiedRace", resultSet.getString("identified_race"));
        dataMap.put("dob", resultSet.getDate("DOB"));
        dataMap.put("phone", resultSet.getString("phone"));
        dataMap.put("city", resultSet.getString("cityname"));
        dataMap.put("state", resultSet.getString("statename"));
        dataMap.put("p_payid", resultSet.getInt("p_payid"));
        dataMap.put("p_payDate", resultSet.getDate("p_pay_date"));
        dataMap.put("p_earnings", resultSet.getDouble("p_earnings"));
        dataMap.put("p_fedTax", resultSet.getDouble("p_fed_tax"));
        dataMap.put("p_fedMed", resultSet.getDouble("p_fed_med"));
        dataMap.put("p_fedSS", resultSet.getDouble("p_fed_ss"));
        dataMap.put("p_stateTax", resultSet.getDouble("p_state_tax"));
        dataMap.put("p_retire401k", resultSet.getDouble("p_retire_401k"));
        dataMap.put("p_healthcare", resultSet.getDouble("p_health_care"));
        dataMap.put("pt_payid", resultSet.getInt("pt_payid"));
        dataMap.put("pt_payDate", resultSet.getDate("pt_pay_date"));
        dataMap.put("pt_earnings", resultSet.getDouble("pt_earnings"));
        dataMap.put("pt_fedTax", resultSet.getDouble("pt_fed_tax"));
        dataMap.put("pt_fedMed", resultSet.getDouble("pt_fed_med"));
        dataMap.put("pt_fedSS", resultSet.getDouble("pt_fed_ss"));
        dataMap.put("pt_stateTax", resultSet.getDouble("pt_state_tax"));
        dataMap.put("pt_hourlyWage", resultSet.getDouble("pt_hourly_wage"));
        dataMap.put("division", resultSet.getString("Name"));
        dataMap.put("jobTitle", resultSet.getString("job_title"));

        return dataMap;
    }
}