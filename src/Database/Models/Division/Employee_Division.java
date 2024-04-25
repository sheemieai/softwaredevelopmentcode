package Database.Models.Division;

import javax.swing.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Employee_Division {
    public int div_ID;
    private final HashSet<Integer> set = new HashSet<>(Set.of(1,2,999));

    public Employee_Division(int div_ID) {
        this.div_ID = div_ID;
    }

    public int getDiv_ID() {
        return div_ID;
    }

    public void setDiv_ID(int div_ID) {
        if (!set.contains(div_ID)) {
            div_ID = 0;
        }

        this.div_ID = div_ID;
    }

    public static Employee_Division setFromTextFields(JTextField divField) {
        try {
            String divisionName = divField.getText();
            return new Employee_Division(convertToDivisionId(divisionName));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static int convertToDivisionId(String divisionName) {
        String divisionLower = String.valueOf(divisionName).toLowerCase();

        HashMap<String, Integer> divisionMap = new HashMap<>();
        divisionMap.put("technology engineering", 1);
        divisionMap.put("marketing", 2);
        divisionMap.put("hq", 999);
        divisionMap.put("remote", 0);

        int divisionID = 0;

        if (divisionMap.containsKey(divisionLower)) {
            divisionID = divisionMap.get(divisionLower);
        }

        return divisionID;
    }
}
