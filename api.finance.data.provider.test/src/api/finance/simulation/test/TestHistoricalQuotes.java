package api.finance.simulation.test;

import api.APIManager;
import api.core.API;
import api.core.histquote.Interval;
import api.core.histquote.TimePeriod;
import api.core.histquote.entity.HistoricalQuotes;

public class TestHistoricalQuotes
{

	public static void main(String[] args)
	{
		APIManager locAPIManager = APIManager.FactoryGetInstance(API.SIMULATION);

		try
		{
			HistoricalQuotes locHistoricalQuotes = locAPIManager.getHistoricalQuotes("BMW", TimePeriod.YEAR_3, Interval.WEEK_1);
			
			System.out.println(locHistoricalQuotes.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
