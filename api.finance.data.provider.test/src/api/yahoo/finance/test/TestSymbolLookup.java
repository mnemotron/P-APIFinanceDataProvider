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
		locSL.setLanguage("de-DE");

		try
		{
			locResSymbolLookup = locSL.getResult();		
			
			System.out.println(locResSymbolLookup.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
