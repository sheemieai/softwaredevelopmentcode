package Test.SearchTest;

import org.junit.jupiter.api.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.Search.SearchEmployeeById.searchEmployeeById;

public class SearchEmployeeByIdTest {
    private Connection conn;

    // Create Tables for Fake MySQL DB
    private String sqlCityTable = "CREATE TABLE city (cityid INT PRIMARY KEY, cityname VARCHAR(255))";

    private String sqlStateTable = "CREATE TABLE state (stateid INT PRIMARY KEY, statename VARCHAR(255))";

    private String sqlAddressTable = "CREATE TABLE address (empid INT PRIMARY KEY, gender VARCHAR(50), pronouns VARCHAR(50), " +
            "identified_race VARCHAR(50), DOB DATE, phone VARCHAR(50), cityid INT, stateid INT, " +
            "FOREIGN KEY (cityid) REFERENCES city(cityid), FOREIGN KEY (stateid) REFERENCES state(stateid))";

    private String sqlEmployeeTable = "CREATE TABLE employees (empid INT PRIMARY KEY, Fname VARCHAR(255), Lname VARCHAR(255), " +
            "email VARCHAR(255), HireDate DATE, Salary DECIMAL(10, 2), SSN VARCHAR(16))";

    private String sqlDivisionTable = "CREATE TABLE division (ID INT PRIMARY KEY, Name VARCHAR(255), city VARCHAR(255), " +
            "addressLine1 VARCHAR(255), addressLine2 VARCHAR(255), state VARCHAR(255), country VARCHAR(255), " +
            "postalCode VARCHAR(50))";

    private String sqlEmployeeDivisionTable = "CREATE TABLE employee_division (empid INT, div_ID INT, PRIMARY KEY (empid, div_ID), " +
            "FOREIGN KEY (empid) REFERENCES employees(empid), FOREIGN KEY (div_ID) REFERENCES division(ID))";

    private String sqlJobTitleTable = "CREATE TABLE job_titles (job_title_id INT PRIMARY KEY, job_title VARCHAR(255))";

    private String sqlEmployeeJobTitleTable = "CREATE TABLE employee_job_titles (empid INT, job_title_id INT, PRIMARY KEY (empid, " +
            "job_title_id), FOREIGN KEY (empid) REFERENCES employees(empid), " +
            "FOREIGN KEY (job_title_id) REFERENCES job_titles(job_title_id))";

    private String sqlPayrollTable = "CREATE TABLE payroll (payID INT PRIMARY KEY, pay_date DATE, earnings DECIMAL(10, 2), " +
            "fed_tax DECIMAL(10, 2), fed_med DECIMAL(10, 2), fed_SS DECIMAL(10, 2), state_tax DECIMAL(10, 2), " +
            "retire_401k DECIMAL(10, 2), health_care DECIMAL(10, 2), empid INT, " +
            "FOREIGN KEY (empid) REFERENCES employees(empid))";

    private String sqlPayrollPTTable = "CREATE TABLE payroll_pt (payID INT PRIMARY KEY, pay_date DATE, earnings DECIMAL(10, 2), " +
            "fed_tax DECIMAL(10, 2), fed_med DECIMAL(10, 2), fed_SS DECIMAL(10, 2), state_tax DECIMAL(10, 2), " +
            "hourly_wage DECIMAL(10, 2), empid INT, FOREIGN KEY (empid) REFERENCES employees(empid))";

    // Insert Data to Tables
    private String sqlCityTableInsert = "INSERT INTO city (cityid, cityname) VALUES (1, 'Austin')";

    private String sqlStateTableInsert = "INSERT INTO state (stateid, statename) VALUES (1, 'TX')";

    private String sqlAddressTableInsert = "INSERT INTO address (empid, gender, pronouns, identified_race, DOB, phone, cityid, stateid) " +
            "VALUES (1, 'male', 'he/him', 'black', '1981-11-28', '404-111-2222', 1, 1), " +
            "(2, 'female', 'she/her', 'black', '1981-11-28', '404-111-2222', 1, 1)";

    private String sqlEmployeeTableInsert = "INSERT INTO employees (empid, Fname, Lname, email, HireDate, Salary, SSN) " +
            "VALUES (1, 'Jay', 'Test', 'jay_test@email.com', '2005-01-25', 70000, '222-222-2222'), " +
            "(2, 'Janet', 'Test', 'janet_test@email.com', '2005-01-25', 70000, '222-222-2223')";

    private String sqlDivisionTableInsert = "INSERT INTO division (ID, Name, city, addressLine1, addressLine2, state, country, postalCode) " +
            "VALUES (0, 'Remote', 'Austin', '300 Congress Ave', '', 'TX', 'USA', '78701')";

    private String sqlEmployeeDivisionTableInsert = "INSERT INTO employee_division (empid, div_ID) VALUES (1, 0), (2, 0)";

    private String sqlJobTitleTableInsert = "INSERT INTO job_titles (job_title_id, job_title) VALUES (800, 'Remote Worker')";

    private String sqlEmployeeJobTitleTableInsert = "INSERT INTO employee_job_titles (empid, job_title_id) " +
            "VALUES (1, 800), (2, 800)";

    private String sqlPayrollTableInsert = "INSERT INTO payroll (empid, payID, pay_date, earnings, fed_tax, fed_med, fed_SS, state_tax, retire_401k, health_care) " +
            "VALUES (1, 1, '1980-01-01', 100, 100, 100, 100, 100, 100, 100)";

    private String sqlPayrollPTTableInsert = "INSERT INTO payroll_pt (empid, payID, pay_date, earnings, fed_tax, fed_med, fed_SS, state_tax, hourly_wage) " +
            "VALUES (2, 2, '1980-01-01', 200, 200, 200, 200, 200, 200)";

    // Fake DB Connection Details
    private String url = "jdbc:mysql://localhost:3306/testdb";
    private String user = "root";
    private String pw = "19251925";
    
    @BeforeEach
    public void setup() throws SQLException {
        conn = DriverManager.getConnection(url, user, pw);

        try (Statement stmt = conn.createStatement()) {
            // Create tables
            stmt.executeUpdate(sqlCityTable);
            stmt.executeUpdate(sqlStateTable);
            stmt.executeUpdate(sqlAddressTable);
            stmt.executeUpdate(sqlEmployeeTable);
            stmt.executeUpdate(sqlDivisionTable);
            stmt.executeUpdate(sqlEmployeeDivisionTable);
            stmt.executeUpdate(sqlJobTitleTable);
            stmt.executeUpdate(sqlEmployeeJobTitleTable);
            stmt.executeUpdate(sqlPayrollTable);
            stmt.executeUpdate(sqlPayrollPTTable);

            // Insert data
            stmt.executeUpdate(sqlCityTableInsert);
            stmt.executeUpdate(sqlStateTableInsert);
            stmt.executeUpdate(sqlAddressTableInsert);
            stmt.executeUpdate(sqlEmployeeTableInsert);
            stmt.executeUpdate(sqlDivisionTableInsert);
            stmt.executeUpdate(sqlEmployeeDivisionTableInsert);
            stmt.executeUpdate(sqlJobTitleTableInsert);
            stmt.executeUpdate(sqlEmployeeJobTitleTableInsert);
            stmt.executeUpdate(sqlPayrollTableInsert);
            stmt.executeUpdate(sqlPayrollPTTableInsert);
        }
    }

    @Test
    public void testSearchEmployeeByIdSuccess() throws ParseException {
        ArrayList<HashMap<String, Object>> actualOutput;
        HashMap<String, Object> expectedOutput = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        expectedOutput.put("empid", 1);
        expectedOutput.put("firstname", "Jay");
        expectedOutput.put("lastname", "Test");
        expectedOutput.put("email", "jay_test@email.com");
        expectedOutput.put("hireDate", new java.sql.Date(sdf.parse("2005-01-25").getTime()));
        expectedOutput.put("salary", 70000.0);
        expectedOutput.put("ssn", "222-222-2222");
        expectedOutput.put("gender", "male");
        expectedOutput.put("pronouns", "he/him");
        expectedOutput.put("identifiedRace", "black");
        expectedOutput.put("dob", new java.sql.Date(sdf.parse("1981-11-28").getTime()));
        expectedOutput.put("phone", "404-111-2222");
        expectedOutput.put("city", "Austin");
        expectedOutput.put("state", "TX");
        expectedOutput.put("p_payid", 1);
        expectedOutput.put("p_payDate", new java.sql.Date(sdf.parse("1980-01-01").getTime()));
        expectedOutput.put("p_earnings", 100.0);
        expectedOutput.put("p_fedTax", 100.0);
        expectedOutput.put("p_fedMed", 100.0);
        expectedOutput.put("p_fedSS", 100.0);
        expectedOutput.put("p_stateTax", 100.0);
        expectedOutput.put("p_retire401k", 100.0);
        expectedOutput.put("p_healthcare", 100.0);
        expectedOutput.put("pt_payid", 0);
        expectedOutput.put("pt_payDate", null);
        expectedOutput.put("pt_earnings", 0.0);
        expectedOutput.put("pt_fedTax", 0.0);
        expectedOutput.put("pt_fedMed", 0.0);
        expectedOutput.put("pt_fedSS", 0.0);
        expectedOutput.put("pt_stateTax", 0.0);
        expectedOutput.put("pt_hourlyWage", 0.0);
        expectedOutput.put("division", "Remote");
        expectedOutput.put("jobTitle", "Remote Worker");

        actualOutput = searchEmployeeById(1, url, user, pw);

        HashMap<String, Object> actualData = actualOutput.get(0);

        for (String key : expectedOutput.keySet()) {
            Assertions.assertEquals(expectedOutput.get(key), actualData.get(key));
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS payroll_pt");
            stmt.execute("DROP TABLE IF EXISTS payroll");
            stmt.execute("DROP TABLE IF EXISTS employee_job_titles");
            stmt.execute("DROP TABLE IF EXISTS job_titles");
            stmt.execute("DROP TABLE IF EXISTS employee_division");
            stmt.execute("DROP TABLE IF EXISTS division");
            stmt.execute("DROP TABLE IF EXISTS employees");
            stmt.execute("DROP TABLE IF EXISTS address");
            stmt.execute("DROP TABLE IF EXISTS state");
            stmt.execute("DROP TABLE IF EXISTS city");
        }
        // Close the connection
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
