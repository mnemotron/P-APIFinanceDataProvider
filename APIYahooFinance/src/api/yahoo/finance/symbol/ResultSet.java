package api.yahoo.finance.symbol;

import java.util.ArrayList;
import java.util.List;

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

}
