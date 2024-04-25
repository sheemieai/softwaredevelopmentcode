package UX.Panel.Home.Panel;

import UX.Panel.Home.Model.EmployeeDivisionTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Get.GetEmployeeDivisionTableData.getEmployeeDivisionTableData;

public class HomeEmployeeDivisionTablePanel extends JPanel {
    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, String> connectionVariables;
    private JTable table;
    private JScrollPane scrollPane;
    public HomeEmployeeDivisionTablePanel() {
        setLayout(new BorderLayout());

        connectionVariables = getConnectionVariables();

        data = getEmployeeDivisionTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        table = new JTable(new EmployeeDivisionTableModel(data));

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void refreshPanel() {
        data = getEmployeeDivisionTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        EmployeeDivisionTableModel model = new EmployeeDivisionTableModel(data);

        table.setModel(model);

        table.revalidate();
        table.repaint();
    }
}
