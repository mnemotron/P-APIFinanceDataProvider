package api.yahoo.finance.test;

import api.yahoo.finance.symbol.SymbolLookup;
import api.yahoo.finance.symbol.entity.ResSymbolLookup;

public class TestSymbolLookup
{

	public static void main(String[] args)
	{

		ResSymbolLookup locResSymbolLookup;

		SymbolLookup locSL = SymbolLookup.FactoryGetInstance();

		locSL.setQuery("G");
		locSL.setRegion("EU");
		locSL.setLanguage("en-GB");

		try
		{
			locResSymbolLookup = locSL.getResult();			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
