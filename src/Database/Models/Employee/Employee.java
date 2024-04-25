package Database.Models.Employee;

import java.sql.Date;

public interface Employee {
    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    Date getHireDate();

    void setHireDate(Date hireDate);

    double getSalary();

    void setSalary(double salary);

    String getSSN();

    void setSSN(String ssn);
}
