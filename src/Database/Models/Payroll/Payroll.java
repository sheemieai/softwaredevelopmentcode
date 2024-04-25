package Database.Models.Payroll;

import java.sql.Date;

public interface Payroll {
    int getPayID();

    void setPayID(int payID);

    Date getPayDate();

    void setPayDate(Date payDate);

    double getEarnings();

    void setEarnings(double earnings);

    double getFedTax();

    void setFedTax(double fedTax);

    double getFedMed();

    void setFedMed(double fedMed);

    double getFedSS();

    void setFedSS(double fedSS);

    double getStateTax();

    void setStateTax(double stateTax);
}