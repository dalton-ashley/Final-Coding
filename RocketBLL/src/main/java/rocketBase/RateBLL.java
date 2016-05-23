package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		ArrayList<RateDomainModel> rtes = new ArrayList<RateDomainModel>(RateDAL.getAllRates());
		double rte = 0;
		for (RateDomainModel rates : rtes){
			if (GivenCreditScore >= rates.getiMinCreditScore());
				rte = rates.getiMinCreditScore();
			}
			if (rte <= 0){
				throw new RateException(null);
			}
				
		
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return rte;
		
		
	}
	
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(int r, double n, double p, double f, boolean t) throws RateException
	{		double Int = getRate((int) r)/12; 
	 		double term = (n *12); 
	 		double fv = 0; 
	 		double monthly_Payment = Math.abs(FinanceLib.pmt(Int, term, p, fv, t)); 
			return monthly_Payment;  
	}
}
