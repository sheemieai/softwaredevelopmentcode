package UX.Panel.Home.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class AddressTableModel extends AbstractTableModel {
    private final ArrayList<HashMap<String, Object>> data;
    private final String[] columnNames = {"Employee Id", "First Name", "Last Name", "Gender", "Pronouns", "Identified Race", "DOB", "Phone Number", "City", "State"};

    public AddressTableModel(ArrayList<HashMap<String, Object>> data) {
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
            case 1 -> row.get("firstname");
            case 2 -> row.get("lastname");
            case 3 -> row.get("gender");
            case 4 -> row.get("pronouns");
            case 5 -> row.get("identifiedRace");
            case 6 -> row.get("dob");
            case 7 -> row.get("phone");
            case 8 -> row.get("city");
            case 9 -> row.get("state");
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}


