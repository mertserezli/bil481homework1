package com.mycompany.app;

import java.util.ArrayList;
import static spark.Spark.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.Arrays;


public class App
{
    private static int[] tempMergArr;
    private static boolean set=false;
    

	public static int[] doMergeSort(int[] array,int lowerIndex, int higherIndex)
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
	private static void mergeParts(int[] array,int lowerIndex, int middle, int higherIndex) {
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




	public static void main(String[] args) {
		port(getHerokuAssignedPort());
		get("/", (req, res) -> "Hello, World");
		post("/compute", (req, res) -> {
		//System.out.println(req.queryParams("input1"));
		//System.out.println(req.queryParams("input2"));
		String input1 = req.queryParams("input1");
		java.util.Scanner sc1 = new java.util.Scanner(input1);
		sc1.useDelimiter("[;\r\n]+");
		java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
		while (sc1.hasNext())
		{
			int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
			inputList.add(value);
		}
		System.out.println(inputList);
	
		String input2 = req.queryParams("input2").replaceAll("\\s","");
		int input2AsInt = Integer.parseInt(input2);
		String input3 = req.queryParams("input3").replaceAll("\\s","");
		int input3AsInt = Integer.parseInt(input3);

		int[] arr = new int[inputList.size()];
    	for (int i=0; i < arr.length; i++)
    	{
        	arr[i] = inputList.get(i).intValue();
    	}

		String result=Arrays.toString( App.doMergeSort(arr, input2AsInt, input3AsInt));

		Map map = new HashMap();
		map.put("result", result);
		return new ModelAndView(map, "compute.mustache");
		}, new MustacheTemplateEngine());
	
		get("/compute",(rq, rs) -> {
			Map map = new HashMap();
			map.put("result", "not computed yet!");
			return new ModelAndView(map, "compute.mustache");
		},new MustacheTemplateEngine());
	}
	static int getHerokuAssignedPort()
	{
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null)
		{
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}
}
