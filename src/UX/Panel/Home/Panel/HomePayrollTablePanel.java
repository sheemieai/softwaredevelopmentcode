package UX.Panel.Home.Panel;

import UX.Panel.Home.Model.PayrollTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Get.GetPayrollTableData.getPayrollTableData;

public class HomePayrollTablePanel extends JPanel {
    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, String> connectionVariables;
    private JTable table;
    private JScrollPane scrollPane;
    public HomePayrollTablePanel() {
        setLayout(new BorderLayout());

        connectionVariables = getConnectionVariables();

        data = getPayrollTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        table = new JTable(new PayrollTableModel(data));

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void refreshPanel() {
        data = getPayrollTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        PayrollTableModel model = new PayrollTableModel(data);

        table.setModel(model);

        table.revalidate();
        table.repaint();
    }
}
