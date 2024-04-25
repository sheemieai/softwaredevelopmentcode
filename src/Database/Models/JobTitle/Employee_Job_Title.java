package Database.Models.JobTitle;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Employee_Job_Title {
    public int job_title_id;
    private final HashSet<Integer> set = new HashSet<>(Set.of(100, 101, 102, 103, 200, 201, 202,
            900, 901, 902));

    public Employee_Job_Title(int job_title_id) {
        this.job_title_id = job_title_id;
    }

    public int getJob_title_id() {
        return job_title_id;
    }

    public void setJob_title_id(int job_title_id) {
        if (!set.contains(job_title_id)) {
            job_title_id = 800;
        }

        this.job_title_id = job_title_id;
    }

    public static Employee_Job_Title setFromTextFields(JTextField jobTitleField) {
        try {
            String jobTitleName = jobTitleField.getText();
            return new Employee_Job_Title(convertToJobTitleId(jobTitleName));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static int convertToJobTitleId(String jobTitleName) {
        String jobTitleLower = String.valueOf(jobTitleName);

        HashMap<String, Integer> jobTitleMap = new HashMap<>();
        jobTitleMap.put("software manager", 100);
        jobTitleMap.put("software architect", 101);
        jobTitleMap.put("software engineer", 102);
        jobTitleMap.put("software developer", 103);
        jobTitleMap.put("marketing manager", 200);
        jobTitleMap.put("marketing associate", 201);
        jobTitleMap.put("marketing assistant", 202);
        jobTitleMap.put("remote worker", 800);
        jobTitleMap.put("chief exec. officer", 900);
        jobTitleMap.put("chief finn. officer", 901);
        jobTitleMap.put("chief info. officer", 902);

        int jobTitleID = 800;

        if (jobTitleMap.containsKey(jobTitleLower)) {
            jobTitleID = jobTitleMap.get(jobTitleLower);
        }

        return jobTitleID;
    }
}
