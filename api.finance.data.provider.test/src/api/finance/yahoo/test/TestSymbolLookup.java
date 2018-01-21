package api.finance.yahoo.test;

import api.APIManager;
import api.core.API;
import api.core.ticker.entity.Tickers;

public class TestSymbolLookup
{

	public static void main(String[] args)
	{
		APIManager locAPIManager = APIManager.FactoryGetInstance(API.YAHOO_FINANCE);

		try
		{
			Tickers locTickers = locAPIManager.searchTicker("B");
			// locSL.setRegion("EU");
			// locSL.setLanguage("de-DE");

			System.out.println(locTickers.toString());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
