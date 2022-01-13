package com.ecommerce.stepdefinition;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Rerun implements IRetryAnalyzer{
	
	int i = 0;
	int j = 2;

	public boolean retry(ITestResult arg0) {
		
		if(i < j){
			
			i++;
			
			System.out.println("Failure method name :"+arg0.getName());
			
			return true;
			
		}
		
		return false;
	}

}
