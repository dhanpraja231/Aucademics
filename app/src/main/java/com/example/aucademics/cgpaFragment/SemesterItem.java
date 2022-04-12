package com.example.aucademics.cgpaFragment;

public class SemesterItem {
    Integer semester;
    Double cgpa;

    public SemesterItem(Integer semester, Double cgpa) {
        this.semester = semester;
        this.cgpa = cgpa;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "SemesterItem{" +
                "semester=" + semester +
                ", cgpa=" + cgpa +
                '}';
    }
}
