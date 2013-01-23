package com.william.demo.tdd.student;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 13-1-3
 * Time: 上午11:57
 * To change this template use File | Settings | File Templates.
 */
public class StudentTest extends TestCase {
    public void testCreate()
    {
        final String firstStudentName = "Jane Doe";
        final String secondStudentName = "Jow Blow";
        Student firstStudent = new Student(firstStudentName);
        assertEquals(firstStudentName, firstStudent.getName());

        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getName());
    }
}
