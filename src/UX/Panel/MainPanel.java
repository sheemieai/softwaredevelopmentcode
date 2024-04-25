package UX.Panel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

import UX.Panel.Form.UpdateEmployeeForm.Panel.UpdateEmployeeFormPanel;
import UX.Panel.Helper.PanelChangeListener;
import UX.Panel.Form.SearchEmployeeForm.Panel.SearchEmployeeFormPanel;
import UX.Panel.Header.HeaderPanel;
import UX.Panel.Form.AddEmployeeForm.AddEmployeeFormPanel;
import UX.Panel.Form.DeleteEmployeeForm.DeleteEmployeeFormPanel;
import UX.Panel.Home.HomePanel;

public class MainPanel extends JPanel implements PanelChangeListener {
    private JPanel contentPanel;
    private JPanel headerContentPanel;
    private CardLayout cardLayout;
    private CardLayout headerCardLayout;

    public MainPanel(int headerCase, int formTrigger) {
        setLayout(new BorderLayout());
        setBackground(new Color(0xe3d9d0));
        //Border
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        setBorder(border);
        // Switching Panels
        headerCardLayout = new CardLayout();
        headerContentPanel = new JPanel(headerCardLayout);
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        // Panels
        headerContentPanel.add( new HeaderPanel("Dashboard", "Home"), "Home");
        headerContentPanel.add( new HeaderPanel("Dashboard", "Add Employee"), "AddEmployee");
        headerContentPanel.add( new HeaderPanel("Dashboard", "Search Employee"), "SearchEmployee");
        headerContentPanel.add( new HeaderPanel("Dashboard", "Delete Employee"), "DeleteEmployee");
        headerContentPanel.add( new HeaderPanel("Dashboard", "Update Employee"), "UpdateEmployee");
        add(headerContentPanel, BorderLayout.NORTH);

        HomePanel homePanel = new HomePanel(formTrigger);
        AddEmployeeFormPanel addEmployeeFormPanel = new AddEmployeeFormPanel();
        SearchEmployeeFormPanel searchEmployeeFormPanel = new SearchEmployeeFormPanel(formTrigger);
        DeleteEmployeeFormPanel deleteEmployeeFormPanel = new DeleteEmployeeFormPanel();
        UpdateEmployeeFormPanel updateEmployeeFormPanel = new UpdateEmployeeFormPanel(formTrigger);

        contentPanel.add(homePanel, "Home");
        contentPanel.add(addEmployeeFormPanel, "AddEmployee");
        contentPanel.add(searchEmployeeFormPanel, "SearchEmployee");
        contentPanel.add(deleteEmployeeFormPanel, "DeleteEmployee");
        contentPanel.add(updateEmployeeFormPanel, "UpdateEmployee");
        add(contentPanel, BorderLayout.CENTER);

        switch (headerCase) {
            case 1:
                headerCardLayout.show(headerContentPanel, "Home");
                cardLayout.show(contentPanel, "Home");
                break;
            case 2:
                headerCardLayout.show(headerContentPanel, "AddEmployee");
                cardLayout.show(contentPanel, "AddEmployee");
                break;
            case 3:
                headerCardLayout.show(headerContentPanel, "SearchEmployee");
                cardLayout.show(contentPanel, "SearchEmployee");
                break;
            case 4:
                headerCardLayout.show(headerContentPanel, "DeleteEmployee");
                cardLayout.show(contentPanel, "DeleteEmployee");
                break;
            case 5:
                headerCardLayout.show(headerContentPanel, "UpdateEmployee");
                cardLayout.show(contentPanel, "UpdateEmployee");
                break;
            default:
                headerCardLayout.show(headerContentPanel, "Home");
                cardLayout.show(contentPanel, "Home");
                break;
        }
    }

    @Override
    public void onPanelChange(String panelName) {
        cardLayout.show(contentPanel, panelName);
        headerCardLayout.show(headerContentPanel, panelName);
    }
}
