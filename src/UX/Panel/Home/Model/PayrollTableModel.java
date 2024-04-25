package UX.Panel.Home.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class PayrollTableModel extends AbstractTableModel {
    private final ArrayList<HashMap<String, Object>> data;
    private final String[] columnNames = {"Employee Id", "Pay Id", "First Name", "Last Name", "Pay Date", "Earnings", "Fed Tax", "Fed Med", "Fed SS", "State Tax", "Retire 401k", "Health Care"};

    public PayrollTableModel(ArrayList<HashMap<String, Object>> data) {
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
            case 1 -> row.get("payid");
            case 2 -> row.get("firstname");
            case 3 -> row.get("lastname");
            case 4 -> row.get("payDate");
            case 5 -> row.get("earnings");
            case 6 -> row.get("fedTax");
            case 7 -> row.get("fedMed");
            case 8 -> row.get("fedSS");
            case 9 -> row.get("stateTax");
            case 10 -> row.get("retire401k");
            case 11 -> row.get("healthcare");
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}


