package com.concerto.lp.constants;

import java.util.ArrayList;
import java.util.Arrays;

import com.concerto.lp.model.Collateral;

public class LoanConstants {
	
	  private static final ArrayList<Collateral> COLLATERAL_TYPE = 
			  (ArrayList<Collateral>)Arrays.asList(new Collateral("1", "Vehicle registration or title")
					  ,new Collateral("2", "home title")
					  ,new Collateral("3", "insurance documents")
					  ,new Collateral("4", "fixed deposits")); 
	  private static final String[] LOAN_TYPE = {"Buying home","Buying home","Education","Marriage","Hospitalization"};
	  private static final String[] ID_TYPE = {"Passport", "Driving License","Aadhar"}; 
	  private static final String LOANTABLE = "MEHMOOD_LOAN"; 
	  private static final String CUSTOMERTABLE = "MEHMOOD_CUSTOMER";
	  private static final String EMPLOYEETABLE = "MEHMOOD_EMPLOYEE";
	  private static final String LOANCOLLATERALTABLE = "MEHMOOD_LOANCOLLATERAL";
	  private static final String COLLATERALTABLE = "MEHMOOD_COLLATERAL";
	  
	  public static double calculateRate(double period)
		{
		  System.out.println("Period1 "+period);
			if(period <= 2)
				return 0.05d;
			else if(period >2 && period <5)
				return 0.06d;
			else if(period >=5 && period < 8)
				return 0.08d;
			else
				return 0.085d;
		}
	  
	 
}
