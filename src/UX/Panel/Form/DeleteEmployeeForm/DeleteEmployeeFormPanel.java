package UX.Panel.Form.DeleteEmployeeForm;

import UX.Button.MainPanelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import static Database.Connection.Connection.getConnectionVariables;
import static Database.Delete.DeleteEmployeeById.deleteEmployeeById;

public class DeleteEmployeeFormPanel extends JPanel {
    private JTextField deleteEmployeeIdField;
    private HashMap<String, String> connectionVariables;

    public DeleteEmployeeFormPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set the initial grid bag layout constraints
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Delete by Id
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Delete Employee by Employee Id:"), gbc);

        deleteEmployeeIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(deleteEmployeeIdField, gbc);

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

        connectionVariables = getConnectionVariables();

        submitButton.addActionListener(e -> {
            int empid = Integer.parseInt(deleteEmployeeIdField.getText());
            HashMap<String, String> message;
            try {
                message = deleteEmployeeById(empid, connectionVariables.get("url"), connectionVariables.get("user"),
                        connectionVariables.get("pw"));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            alertLabel.setText(message.get("message"));

            timer.setRepeats(false);
            timer.start();
        });
    }
}
