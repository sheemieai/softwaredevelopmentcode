package UX.Panel.Home.Panel;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;

import UX.Panel.Home.Model.EmployeeTableModel;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Get.GetEmployeeTableData.getEmployeeTableData;

public class HomeEmployeeTablePanel extends JPanel {
    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, String> connectionVariables;
    private JTable table;
    private JScrollPane scrollPane;
    public HomeEmployeeTablePanel() {
        setLayout(new BorderLayout());

        connectionVariables = getConnectionVariables();

        data = getEmployeeTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        table = new JTable(new EmployeeTableModel(data));

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void refreshPanel() {
        data = getEmployeeTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        table.setModel(new EmployeeTableModel(data));

        table.revalidate();
        table.repaint();
    }
}
