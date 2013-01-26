package com.william.demo.tdd.student;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 13-1-26
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
public class CourseSessionTest extends TestCase {
    private CourseSession session;

    public void setUp()
    {
        session = new CourseSession("ENGL", 101);
    }

    public void testCreate()
    {
        assertEquals("ENGL", session.getDepartment());
        assertEquals(101, session.getNumber());
        assertEquals(0, session.getNumberOfStudents());

    }

    public void testEnrollStudents()
    {
        Student student1 = new Student("Caive Divoe");
        session.enroll(student1);
        assertEquals(student1, session.get(0));

        Student student2 = new Student("Coralee Deva");
        session.enroll(student2);
        assertEquals(student2, session.get(1));
    }
}
