package com.example.aucademics.bunkFragment;

public class BunkItem {
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
                ", totalHours=" + totalHours +
                ", bunkedHours=" + bunkedHours +
                ", bunkHoursLeft=" + bunkHoursLeft +
                ", attendancePercent=" + attendancePercent +
                '}';
    }

    public BunkItem(String subject_name1, String totalHours1){
        subject_name = subject_name1;
        totalHours = Integer.parseInt(totalHours1);
        bunkedHours = 0;
        bunkHoursLeft = totalHours/4 - bunkedHours;
        attendancePercent = (float)(totalHours-bunkedHours)/(float)totalHours*100.0f;
    }

    public BunkItem(String subject_name, String totalHours,String bunkedHours) {
        this.subject_name = subject_name;
        this.totalHours = Integer.parseInt(totalHours);
        this.bunkedHours = Integer.parseInt(bunkedHours);
        bunkHoursLeft = this.totalHours/4 - this.bunkedHours;
        attendancePercent = ((float)(this.totalHours-this.bunkedHours)/(float)this.totalHours)*100.0f;
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

    public void setBunkedHours(int bunkedHours) {
        this.bunkedHours = bunkedHours;
    }

    public void setBunkHoursLeft(int bunkHoursLeft) {
        this.bunkHoursLeft = bunkHoursLeft;
    }

    public void setAttendancePercent(float attendancePercent) {
        this.attendancePercent = attendancePercent;
    }
}
