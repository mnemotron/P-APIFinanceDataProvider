package api.finance.google.test;

import api.APIManager;
import api.core.API;
import api.core.histquote.TimePeriod;
import api.core.histquote.entity.HistoricalQuotes;

public class TestHistoricalQuotes
{

	public static void main(String[] args)
	{
		APIManager locAPIManager = APIManager.FactoryGetInstance(API.GOOGLE_FINANCE);

		try
		{	
			HistoricalQuotes locHistoricalQuotes = locAPIManager.getHistoricalQuotes("g", TimePeriod.YEAR_1, null);
			
			System.out.println(locHistoricalQuotes.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
