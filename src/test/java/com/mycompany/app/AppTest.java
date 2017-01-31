package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.Arrays;


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
    public void testApp()
    {
        assertTrue( true );
    }
	public void testSorted()
	{
		int[] array={5,4,3,2,1};
		int[] result={1,2,3,4,5};
		assertTrue(Arrays.equals( result, App.doMergeSort(array,0,array.length-1)));
	}
	public void testNull()
	{
		assertTrue(null==App.doMergeSort(null,0,15));
	}
	public void testEmpty()
	{
		assertTrue(null==App.doMergeSort(null,0,15));
	}
	public void testSameIndex()
	{
		int[] array={5,4,3,2,1,0};
		int[] expected={5,4,3,2,1,0};
		assertTrue(Arrays.equals(expected, App.doMergeSort(array,2,2)));
	}
	public void testLowerRight()
	{
		int[] array={5,4,3,2,1,0};
		int[] expected={5,4,3,2,1,0};
		assertTrue(null==App.doMergeSort(array,5,2));
	}
	
}
