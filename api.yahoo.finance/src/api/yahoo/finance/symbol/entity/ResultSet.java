package api.yahoo.finance.symbol.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Result Set
 * @author  mnemotron
 * @version 1.0.0
 * @since 2018-01-01
 */
public class ResultSet
{
	private String query;
	private List<Symbol> result = new ArrayList<Symbol>();
	
	public ResultSet()
	{
		
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public List<Symbol> getResult()
	{
		return result;
	}

	public void setResult(List<Symbol> result)
	{
		this.result = result;
	}
	
	public String toString()
	{
		StringBuilder locStringBuilder = new StringBuilder();
		
		locStringBuilder.append("query: " + this.getQuery() + "\n");
		
		for (Symbol symbol : result)
		{
			locStringBuilder.append(symbol.toString() + "\n");
		}
		
		return locStringBuilder.toString();
	}

}
