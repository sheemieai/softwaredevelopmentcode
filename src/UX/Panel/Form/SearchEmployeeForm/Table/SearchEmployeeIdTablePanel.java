package UX.Panel.Form.SearchEmployeeForm.Table;

import UX.Panel.Form.SearchEmployeeForm.Model.AllEmployeeDataModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Search.SearchEmployeeById.searchEmployeeById;

public class SearchEmployeeIdTablePanel extends JPanel {
    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, String> connectionVariables;
    private JTable table;
    private JScrollPane scrollPane;

    public SearchEmployeeIdTablePanel(int empid) {
        setLayout(new BorderLayout());

        connectionVariables = getConnectionVariables();

        data = searchEmployeeById(empid, connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        ArrayList<HashMap<String, Object>> dataModified = changeHashMapKeys(data);

        table = new JTable(new AllEmployeeDataModel(dataModified));

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }

    private ArrayList<HashMap<String, Object>> changeHashMapKeys(ArrayList<HashMap<String, Object>> array) {
        ArrayList<HashMap<String, Object>> arrayModified = new ArrayList<>();

        for (HashMap<String, Object> originalMap : array) {
            HashMap<String, Object> mapModified = new HashMap<>();
            for (Map.Entry<String, Object> map : originalMap.entrySet()) {
                String key = map.getKey();
                Object value = map.getValue();

                if (value == null || (value instanceof Number && ((Number) value).doubleValue() == 0.0)) {
                    continue;
                }

                if (key.startsWith("p_") || key.startsWith("pt_")) {
                    key = key.substring(key.indexOf('_') + 1);
                }
                mapModified.put(key, value);
            }

            arrayModified.add(mapModified);
        }

        return arrayModified;
    }

    public HashMap<String, String> getPanelMessage(int empid) {
        connectionVariables = getConnectionVariables();

        data = searchEmployeeById(empid, connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        HashMap<String, String> response = new HashMap<>();

        if (data.isEmpty()) {
            response.put("message", "No Employee Id Found.");
            return response;
        }

        response.put("message", "Employee Id Found.");
        return response;
    }
}
