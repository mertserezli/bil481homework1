package com.mycompany.app;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App
{
    private int[] tempMergArr;
    private boolean set=false;
    
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
	public int[] doMergeSort(int[] array,int lowerIndex, int higherIndex)
	{
		if(array==null)
			return null;
		if(array.length==0)
			return null;
		if(higherIndex-lowerIndex < 0)
			return null;
		if(!set)
		{
			tempMergArr=new int[higherIndex+1];
			set=!set;
		}
		if (lowerIndex < higherIndex)
		{
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			doMergeSort(array ,lowerIndex, middle);
			doMergeSort(array ,middle + 1, higherIndex);
			mergeParts(array,lowerIndex, middle, higherIndex);
		}
		return array;
	}
	private void mergeParts(int[] array,int lowerIndex, int middle, int higherIndex) {
		for(int i = lowerIndex; i <= higherIndex; i++)
		{
			tempMergArr[i] = array[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		while (i <= middle && j <= higherIndex) {
		if (tempMergArr[i] <= tempMergArr[j])
			{
				array[k] = tempMergArr[i];
				i++;
			}
			else
			{
				array[k] = tempMergArr[j];
				j++;
			}
			k++;
		}
		while (i <= middle)
		{
			array[k] = tempMergArr[i];
			k++;
			i++;
		}
	}
}
