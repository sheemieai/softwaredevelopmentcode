package Database.Models.Payroll;

import java.sql.Date;

public class General_Payroll implements Payroll {
    public int payID;
    public Date payDate;
    public double earnings;
    public double fedTax;
    public double fedMed;
    public double fedSS;
    public double stateTax;

    public General_Payroll(Date payDate, double salary) {
        this.payDate = payDate;
        this.earnings = calculateEarnings(salary);
        this.fedTax = calculateFedTax(salary);
        this.fedMed = calculateFedMed(salary);
        this.fedSS = calculateFedSS(salary);
        this.stateTax = calculateStateTax(salary);
    }

    private double calculateEarnings(double salary) {
        return salary / 52.0;
    }

    private double calculateFedTax(double salary) {
        return (salary / 52.0) * 0.32;
    }

    private double calculateFedMed(double salary) {
        return (salary / 52.0) * 0.0145;
    }

    private double calculateFedSS(double salary) {
        return (salary / 52.0) * 0.062;
    }

    private double calculateStateTax(double salary) {
        return (salary / 52.0) * 0.12;
    }

    @Override
    public int getPayID() {
        return payID;
    }

    @Override
    public void setPayID(int payID) {
        this.payID = payID;
    }

    @Override
    public Date getPayDate() {
        return payDate;
    }

    @Override
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public double getEarnings() {
        return earnings;
    }

    @Override
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    @Override
    public double getFedTax() {
        return fedTax;
    }

    @Override
    public void setFedTax(double fedTax) {
        this.fedTax = fedTax;
    }

    @Override
    public double getFedMed() {
        return fedMed;
    }

    @Override
    public void setFedMed(double fedMed) {
        this.fedMed = fedMed;
    }

    @Override
    public double getFedSS() {
        return fedSS;
    }

    @Override
    public void setFedSS(double fedSS) {
        this.fedSS = fedSS;
    }

    @Override
    public double getStateTax() {
        return stateTax;
    }

    @Override
    public void setStateTax(double stateTax) {
        this.stateTax = stateTax;
    }
}
