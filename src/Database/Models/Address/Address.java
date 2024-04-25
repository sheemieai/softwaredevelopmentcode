package Database.Models.Address;

import javax.swing.*;
import java.sql.Date;
import java.util.HashMap;

public class Address {
    public String gender;
    public String pronouns;
    public String identifiedRace;
    public Date dob;
    public String phone;
    public int cityId;
    public int stateId;

    public Address(String gender, String pronouns, String identifiedRace, Date dob, String phone, int cityId, int stateId) {
        this.gender = gender;
        this.pronouns = pronouns;
        this.identifiedRace = identifiedRace;
        this.dob = dob;
        this.phone = phone;
        this.cityId = cityId;
        this.stateId = stateId;
    }


    public String getGender() {
        return gender;
    }

    public String getPronouns() {
        return pronouns;
    }

    public String getIdentifiedRace() {
        return identifiedRace;
    }

    public Date getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public int getCityId() {
        return cityId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public void setIdentifiedRace(String identifiedRace) {
        this.identifiedRace = identifiedRace;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public static Address setFromTextFields(JTextField genderField, JTextField pronounsField,
                                            JTextField identifiedRaceField, JTextField DOBField,
                                            JTextField phoneNumberField, JTextField cityField,
                                            JTextField stateField) {
        try {
            String gender = genderField.getText();
            String pronouns = pronounsField.getText();
            String identifiedRace = identifiedRaceField.getText();
            Date dob = Date.valueOf(DOBField.getText());
            String phoneNumber = phoneNumberField.getText();
            String cityName = cityField.getText();
            String stateName = stateField.getText();
            return new Address(gender, pronouns, identifiedRace, dob, phoneNumber, convertToCityId(cityName),
                    convertToStateId(stateName));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static int convertToCityId(String cityText) {
        String cityTextLower = String.valueOf(cityText).toLowerCase();

        HashMap<String, Integer> cityMap = new HashMap<>();
        cityMap.put("atlanta", 1);
        cityMap.put("new york", 2);
        cityMap.put("austin", 3);
        int cityId = 3;

        if (cityMap.containsKey(cityTextLower)) {
            cityId = cityMap.get(cityTextLower);
        }

        return cityId;
    }

    private static int convertToStateId(String stateText) {
        String stateTextLower = String.valueOf(stateText).toLowerCase();

        HashMap<String, Integer> stateMap = new HashMap<>();
        stateMap.put("ga", 1);
        stateMap.put("ny", 2);
        stateMap.put("tx", 3);
        int stateId = 3;

        if (stateMap.containsKey(stateTextLower)) {
            stateId = stateMap.get(stateTextLower);
        }

        return stateId;
    }
}