package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {
	
	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	private RateDomainModel Rdm = null;
	
	public RateDomainModel getRdm(){
		return Rdm;
	}
	
	public RateException(RateDomainModel RD){
		Rdm = RD;
	}
}
