package com.etu.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;
/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    /*
    public void testApp()
    {
        assertTrue( true );
    }
    */

    public void testIfStringArraysNull() {
        ArrayList<String> l1 = new ArrayList<>();
        ArrayList<String> l2 = new ArrayList<>(2);
        ArrayList<Integer> l3 = new ArrayList<>(2);
        ArrayList<Integer> l4 = new ArrayList<>(2);
        assertEquals(null,new App().cryptionWords(l1,l2,l3,l4));
    }

    public void testIfIntegerArraysNull() {
        ArrayList<String> l1 = new ArrayList<>(2);
        ArrayList<String> l2 = new ArrayList<>(2);
        ArrayList<Integer> l3 = new ArrayList<>();
        ArrayList<Integer> l4 = new ArrayList<>(2);
        assertEquals(null, new App().cryptionWords(l1,l2,l3,l4));
    }

    public void testIfArraysHaveSameSize() {
        ArrayList<String> l1 = new ArrayList<>(1);
        ArrayList<String> l2 = new ArrayList<>(5);
        ArrayList<Integer> l3 = new ArrayList<>(3);
        ArrayList<Integer> l4 = new ArrayList<>(4);
        assertEquals(null, new App().cryptionWords(l1, l2, l3, l4));
    }

    public void testIfOneStringNull() {
        ArrayList<String> l1 = new ArrayList<>(1);
        ArrayList<String> l2 = new ArrayList<>(1);
        ArrayList<Integer> l3 = new ArrayList<>(1);
        ArrayList<Integer> l4 = new ArrayList<>(1);
        l1.add("");
        assertEquals(null, new App().cryptionWords(l1,l2,l3,l4));
    }

    public void testIfOneIntegerLessThanZero() {
        ArrayList<String> l1 = new ArrayList<>(1);
        ArrayList<String> l2 = new ArrayList<>(1);
        ArrayList<Integer> l3 = new ArrayList<>(1);
        ArrayList<Integer> l4 = new ArrayList<>(1);
        l3.add(-1);
        assertEquals(null, new App().cryptionWords(l1,l2,l3,l4));
    }
    
}
