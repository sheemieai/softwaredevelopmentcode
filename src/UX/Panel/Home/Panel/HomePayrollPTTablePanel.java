package UX.Panel.Home.Panel;

import UX.Panel.Home.Model.PayrollPTTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Get.GetPayrollPTTableData.getPayrollPTTableData;

public class HomePayrollPTTablePanel extends JPanel {
    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, String> connectionVariables;
    private JTable table;
    private JScrollPane scrollPane;
    public HomePayrollPTTablePanel() {
        setLayout(new BorderLayout());

        connectionVariables = getConnectionVariables();

        data = getPayrollPTTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        table = new JTable(new PayrollPTTableModel(data));

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void refreshPanel() {
        data = getPayrollPTTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        PayrollPTTableModel model = new PayrollPTTableModel(data);

        table.setModel(model);

        table.revalidate();
        table.repaint();
    }
}
