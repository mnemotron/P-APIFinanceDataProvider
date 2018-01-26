package api.finance.google.symbol.entity;

import java.util.ArrayList;

/**
 * Response Symbol Lookup
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-26
 */
public class FGResSymbolLookup
{
	private ArrayList<FGSymbol> matches;

	public FGResSymbolLookup()
	{

	}

	public ArrayList<FGSymbol> getMatches()
	{
		return matches;
	}

	public void setMatches(ArrayList<FGSymbol> matches)
	{
		this.matches = matches;
	}
}
