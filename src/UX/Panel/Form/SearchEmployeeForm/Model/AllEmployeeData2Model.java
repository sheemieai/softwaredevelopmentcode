package UX.Panel.Form.SearchEmployeeForm.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class AllEmployeeData2Model extends AbstractTableModel {
    private final ArrayList<HashMap<String, Object>> data;
    private final String[] columnNames = {"Employee Id", "State", "PayID", "Pay Date", "Earnings",
            "Fed Tax", "Fed Med", "Fed SS", "State Tax", "Retire 401k", "Health Care", "Hourly Wage", "Division", "Job Title"};

    public AllEmployeeData2Model(ArrayList<HashMap<String, Object>> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HashMap<String, Object> row = data.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> row.get("empid");
            case 1 -> row.get("state");
            case 2 -> row.get("payid");
            case 3 -> row.get("payDate");
            case 4 -> row.get("earnings");
            case 5 -> row.get("fedTax");
            case 6 -> row.get("fedMed");
            case 7 -> row.get("fedSS");
            case 8 -> row.get("stateTax");
            case 9 -> row.get("retire401k");
            case 10 -> row.get("healthcare");
            case 11 -> row.get("hourlyWage");
            case 12 -> row.get("division");
            case 13 -> row.get("jobTitle");
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
