package UX.Panel.Form.SearchEmployeeForm;

import UX.Button.MainPanelButton;
import UX.Panel.Form.SearchEmployeeForm.Table.SearchEmployeeNameTable2Panel;
import UX.Panel.Form.SearchEmployeeForm.Table.SearchEmployeeNameTablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SearchEmployeeFormNamePanel extends JPanel {
    private SearchEmployeeNameTablePanel searchEmployeeTablePanel;
    private SearchEmployeeNameTable2Panel searchEmployeeTable2Panel;
    private JTextField searchEmployeeNameField;

    public SearchEmployeeFormNamePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set the initial grid bag layout constraints
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Search by Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Search Employee by Name (FirstName LastName):"), gbc);

        searchEmployeeNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(searchEmployeeNameField, gbc);

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
                String fullName = searchEmployeeNameField.getText();
                String[] nameParts = fullName.split("\\s+");

                String firstName = nameParts[0];
                String lastName = nameParts[1];

                searchEmployeeTablePanel = new SearchEmployeeNameTablePanel(firstName, lastName);
                searchEmployeeTable2Panel = new SearchEmployeeNameTable2Panel(firstName, lastName);

                HashMap<String, String> response = searchEmployeeTablePanel.getPanelMessage(firstName, lastName);

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
            } catch (Exception nfe) {
                alertLabel.setText("Invalid input.");
            }

            timer.setRepeats(false);
            timer.start();

            revalidate();
            repaint();
        });
    }
}
