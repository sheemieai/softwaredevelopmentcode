package UX.Panel.Form.SearchEmployeeForm.Panel;

import UX.Panel.ButtonPanel.ButtonRowSearchPanel;
import UX.Panel.Form.SearchEmployeeForm.SearchEmployeeFormNamePanel;
import UX.Panel.Form.SearchEmployeeForm.SearchEmployeeFormSSNPanel;
import UX.Panel.Form.SearchEmployeeForm.SearchEmployeeFormIdPanel;

import javax.swing.*;
import java.awt.*;

public class SearchEmployeeFormPanel extends JPanel {
    ButtonRowSearchPanel buttonRowSearchPanel;
    private JPanel contentPanel = new JPanel(new CardLayout());
    public SearchEmployeeFormPanel(int formTrigger) {
        super(new BorderLayout());
        buttonRowSearchPanel = new ButtonRowSearchPanel();
        add(buttonRowSearchPanel, BorderLayout.NORTH);

        SearchEmployeeFormNamePanel searchEmployeeFormNamePanel = new SearchEmployeeFormNamePanel();
        SearchEmployeeFormSSNPanel searchEmployeeFormSSNPanel = new SearchEmployeeFormSSNPanel();
        SearchEmployeeFormIdPanel searchEmployeeFormIdPanel = new SearchEmployeeFormIdPanel();

        contentPanel.add(searchEmployeeFormNamePanel, "Name");
        contentPanel.add(searchEmployeeFormSSNPanel, "SSN");
        contentPanel.add(searchEmployeeFormIdPanel, "ID");
        add(contentPanel, BorderLayout.CENTER);

        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        switch(formTrigger) {
            case 1:
                cl.show(contentPanel, "Name");
                break;
            case 2:
                cl.show(contentPanel, "SSN");
                break;
            case 3:
                cl.show(contentPanel, "ID");
                break;
            default:
                cl.show(contentPanel, "Name");
                break;
        }

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        JButton searchByIdButton = buttonRowSearchPanel.getSearchByEmployeeIdButton();
        JButton searchByNameButton = buttonRowSearchPanel.getSearchByEmployeeNameButton();
        JButton searchBySSNButton = buttonRowSearchPanel.getSearchByEmployeeSSNButton();
        searchByIdButton.addActionListener(e -> changeFormPanel("ID"));
        searchByNameButton.addActionListener(e -> changeFormPanel("Name"));
        searchBySSNButton.addActionListener(e -> changeFormPanel("SSN"));
    }

    private void changeFormPanel(String panelName) {
        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, panelName);
    }
}
