package UX.Panel.Home.Panel;

import UX.Panel.Home.Model.EmployeeJobTitleTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Get.GetEmployeeJobTitleTableData.getEmployeeJobTitleTableData;

public class HomeEmployeeJobTitleTablePanel extends JPanel {
    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, String> connectionVariables;
    private JTable table;
    private JScrollPane scrollPane;
    public HomeEmployeeJobTitleTablePanel() {
        setLayout(new BorderLayout());

        connectionVariables = getConnectionVariables();

        data = getEmployeeJobTitleTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        table = new JTable(new EmployeeJobTitleTableModel(data));

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void refreshPanel() {
        data = getEmployeeJobTitleTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        EmployeeJobTitleTableModel model = new EmployeeJobTitleTableModel(data);

        table.setModel(model);

        table.revalidate();
        table.repaint();
    }
}
