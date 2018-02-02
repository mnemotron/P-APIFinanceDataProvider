package api.finance.google.test;

import api.APIManager;
import api.core.API;
import api.core.ticker.entity.Tickers;

public class TestSymbolLookup
{

	public static void main(String[] args)
	{
		APIManager locAPIManager = APIManager.FactoryGetInstance(API.GOOGLE_FINANCE);

		try
		{
			Tickers locTickers = locAPIManager.searchTicker("GOOGL");

			System.out.println(locTickers.toString());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
