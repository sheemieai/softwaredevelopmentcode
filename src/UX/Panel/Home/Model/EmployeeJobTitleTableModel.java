package UX.Panel.Home.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeJobTitleTableModel extends AbstractTableModel {
    private final ArrayList<HashMap<String, Object>> data;
    private final String[] columnNames = {"Employee Id", "First Name", "Last Name", "Job Title"};

    public EmployeeJobTitleTableModel(ArrayList<HashMap<String, Object>> data) {
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
            case 3 -> row.get("jobTitle");
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}



