package UX.Panel.Home.Panel;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Get.GetAddressTableData.getAddressTableData;
import UX.Panel.Home.Model.AddressTableModel;

public class HomeAddressTablePanel extends JPanel {
    private ArrayList<HashMap<String, Object>> data;
    private HashMap<String, String> connectionVariables;
    private JTable table;
    private JScrollPane scrollPane;
    public HomeAddressTablePanel() {
        setLayout(new BorderLayout());

        connectionVariables = getConnectionVariables();

        data = getAddressTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        table = new JTable(new AddressTableModel(data));

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void refreshPanel() {
        data = getAddressTableData(connectionVariables.get("url"), connectionVariables.get("user"),
                connectionVariables.get("pw"));

        AddressTableModel model = new AddressTableModel(data);

        table.setModel(model);

        table.revalidate();
        table.repaint();
    }
}
