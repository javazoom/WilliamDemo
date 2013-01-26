package com.william.demo.tdd.student;

import junit.framework.TestSuite;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 13-1-26
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */
public class AllTests  {
    public static TestSuite suite() {
        junit.framework.TestSuite suite = new TestSuite();
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseSessionTest.class);
        return suite;

    }
}
