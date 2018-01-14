package api.yahoo.finance.symbol.entity;

public class ResSymbolLookup
{
	private ResultSet resultset;
	
	public ResSymbolLookup()
	{
		
	}

	public ResultSet getResultset()
	{
		return resultset;
	}

	public void setResultset(ResultSet resultset)
	{
		this.resultset = resultset;
	}

	public String toString()
	{
		return this.resultset.toString();
	}

}
