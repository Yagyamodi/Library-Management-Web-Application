package com.school.work.models;

public class Subject{
    private int SubjectId;
    private String SubjectNumber;
    private String SubjectName;

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int subjectId) {
        SubjectId = subjectId;
    }

    public String getSubjectNumber() {
        return SubjectNumber;
    }

    public void setSubjectNumber(String subjectNumber) {
        SubjectNumber = subjectNumber;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
}