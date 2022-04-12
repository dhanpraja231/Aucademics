package com.example.aucademics.cgpaFragment;

public class gpaItem {
    Integer id;
    String subjectName;
    String subjectCode;
    Integer credits;
    Integer gradeAchieved;
    Integer semOffered;

    public gpaItem(Integer id, String subjectName, String subjectCode, Integer credits, Integer semOffered) {
        this.id =id;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credits = credits;
        this.gradeAchieved = null;
        this.semOffered = semOffered;
    }public gpaItem(Integer id, String subjectName, String subjectCode, Integer credits,Integer gradeAchieved, Integer semOffered) {
        this.id = id;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credits = credits;
        this.gradeAchieved = gradeAchieved;
        this.semOffered = semOffered;
    }
    public gpaItem(String subjectName, String subjectCode, Integer credits, Integer semOffered) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credits = credits;
        this.gradeAchieved = gradeAchieved;
        this.semOffered = semOffered;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public Integer getCredits() {
        return credits;
    }

    public Integer getGradeAchieved() {
        return gradeAchieved;
    }

    public Integer getSemOffered() {
        return semOffered;
    }

    public void setGradeAchieved(Integer gradeAchieved) {
        this.gradeAchieved = gradeAchieved;
    }

    @Override
    public String toString() {
        return "gpaItem{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", credits=" + credits +
                ", gradeAchieved=" + gradeAchieved +
                ", semOffered=" + semOffered +
                '}';
    }
}
