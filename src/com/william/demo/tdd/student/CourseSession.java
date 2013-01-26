package com.william.demo.tdd.student;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 13-1-26
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public class CourseSession {
    private String department;
    private int number;
    private ArrayList<Student> allStudents = new ArrayList<Student>();

    public CourseSession(String department, int number) {
        this.department = department;
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public int getNumber() {
        return number;  //To change body of created methods use File | Settings | File Templates.
    }

    public int getNumberOfStudents() {
        return allStudents.size();
    }

    public void enroll(Student student) {
        //To change body of created methods use File | Settings | File Templates.
        allStudents.add(student);

    }

    public ArrayList<Student> getStudents() {
        return allStudents;  //To change body of created methods use File | Settings | File Templates.
    }

    public Student get(int index) {
        return allStudents.get(index);
    }
}
