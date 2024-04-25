package UX.Panel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

import UX.Panel.Helper.PanelChangeListener;
import UX.Button.SidePanelButton;
import UX.Panel.Logo.LogoPanel;

public class SidePanel extends JPanel {
    private PanelChangeListener listener;

    public SidePanel(PanelChangeListener listener) {
        this.listener = listener;
        addButtons();
    }
    private SidePanelButton homePageButton;
    private SidePanelButton addEmployeePageButton;
    private SidePanelButton searchEmployeePageButton;
    private SidePanelButton deleteEmployeePageButton;
    private SidePanelButton updateEmployeePageButton;
    public void addButtons() {
        // Set the layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0xe3d9d0));
        //Border
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        setBorder(border);
        //Logo
        LogoPanel logoPanel = new LogoPanel("./src/UX/Img/Logo/logo.png");
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logoPanel);
        // Buttons
        homePageButton = new SidePanelButton("Dashboard", 50);
        addEmployeePageButton = new SidePanelButton("Add Employee", 50);
        searchEmployeePageButton = new SidePanelButton("Search Employee", 50);
        deleteEmployeePageButton = new SidePanelButton("Delete Employee", 50);
        updateEmployeePageButton = new SidePanelButton("Update Employee", 50);

        add(homePageButton);
        add(addEmployeePageButton);
        add(searchEmployeePageButton);
        add(deleteEmployeePageButton);
        add(updateEmployeePageButton);

        getHomePageButton().addActionListener(e -> listener.onPanelChange("Home"));
        getAddEmployeePageButton().addActionListener(e -> listener.onPanelChange("AddEmployee"));
        getSearchEmployeePageButton().addActionListener(e -> listener.onPanelChange("SearchEmployee"));
        getDeleteEmployeePageButton().addActionListener(e -> listener.onPanelChange("DeleteEmployee"));
        getUpdateEmployeePageButton().addActionListener(e -> listener.onPanelChange("UpdateEmployee"));
    }

    public JButton getHomePageButton() {
        return homePageButton;
    }

    public JButton getAddEmployeePageButton() {
        return addEmployeePageButton;
    }

    public JButton getSearchEmployeePageButton() {
        return searchEmployeePageButton;
    }

    public JButton getDeleteEmployeePageButton() {
        return deleteEmployeePageButton;
    }

    public JButton getUpdateEmployeePageButton() {
        return updateEmployeePageButton;
    }
}
