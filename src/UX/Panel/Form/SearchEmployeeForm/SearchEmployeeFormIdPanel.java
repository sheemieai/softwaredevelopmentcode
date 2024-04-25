package UX.Panel.Form.SearchEmployeeForm;

import UX.Button.MainPanelButton;
import UX.Panel.Form.SearchEmployeeForm.Table.SearchEmployeeIdTable2Panel;
import UX.Panel.Form.SearchEmployeeForm.Table.SearchEmployeeIdTablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SearchEmployeeFormIdPanel extends JPanel {
    private SearchEmployeeIdTablePanel searchEmployeeTablePanel;
    private SearchEmployeeIdTable2Panel searchEmployeeTable2Panel;
    private JTextField searchEmployeeIdField;

    public SearchEmployeeFormIdPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set the initial grid bag layout constraints
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Search by Id
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Search Employee by Employee Id:"), gbc);

        searchEmployeeIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(searchEmployeeIdField, gbc);

        MainPanelButton submitButton = new MainPanelButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(submitButton, gbc);

        JLabel alertLabel = new JLabel("");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(alertLabel, gbc);

        Timer timer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alertLabel.setText("");
            }
        });

        submitButton.addActionListener(e -> {
            if (searchEmployeeTablePanel != null) {
                remove(searchEmployeeTablePanel);
            }
            if (searchEmployeeTable2Panel != null) {
                remove(searchEmployeeTable2Panel);
            }

            try {
                int empid = Integer.parseInt(searchEmployeeIdField.getText());

                searchEmployeeTablePanel = new SearchEmployeeIdTablePanel(empid);
                searchEmployeeTable2Panel = new SearchEmployeeIdTable2Panel(empid);

                HashMap<String, String> response = searchEmployeeTablePanel.getPanelMessage(empid);

                alertLabel.setText(response.get("message"));

                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                add(searchEmployeeTablePanel, gbc);

                gbc.gridx = 0;
                gbc.gridy = 4;
                add(searchEmployeeTable2Panel, gbc);
            } catch (NumberFormatException nfe) {
                alertLabel.setText("Input is not a number.");
            }

            timer.setRepeats(false);
            timer.start();

            revalidate();
            repaint();
        });
    }
}
