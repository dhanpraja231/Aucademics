package com.example.aucademics;

public class BunkList {
    private String subject_name;
    private int credits;
    private int totalHours;
    private int bunkedHours;
    private int bunkHoursLeft;
    private float attendancePercent;

    @Override
    public String toString() {
        return "BunkList{" +
                "subject_name='" + subject_name +
                ", credits=" + credits +
                ", totalHours=" + totalHours +
                ", bunkedHours=" + bunkedHours +
                ", bunkHoursLeft=" + bunkHoursLeft +
                ", attendancePercent=" + attendancePercent +
                '}';
    }

    public BunkList(String subject_name1, int credits1, int totalHours1, int bunkedHours1){
        subject_name = subject_name1;
        credits = credits1;
        totalHours = totalHours1;
        bunkedHours = bunkedHours1;
        bunkHoursLeft = totalHours/4 - bunkedHours;
        attendancePercent = (float)(totalHours-bunkedHours)/(float)bunkedHours;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public int getCredits() {
        return credits;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public int getBunkedHours() {
        return bunkedHours;
    }

    public int getBunkHoursLeft() {
        return bunkHoursLeft;
    }

    public float getAttendancePercent() {
        return attendancePercent;
    }


}
