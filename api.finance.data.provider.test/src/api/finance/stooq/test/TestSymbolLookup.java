package api.finance.stooq.test;

import api.APIManager;
import api.core.API;
import api.core.ticker.entity.Tickers;

public class TestSymbolLookup {

	public static void main(String[] args) {
		
		APIManager locAPIManager = APIManager.FactoryGetInstance(API.STOOQ);

		try
		{
			Tickers locTickers = locAPIManager.searchTicker("G");

			System.out.println(locTickers.toString()); 

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
