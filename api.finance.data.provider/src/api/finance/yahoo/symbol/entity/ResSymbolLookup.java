package api.finance.yahoo.symbol.entity;

/**
 * Response Symbol Lookup
 * @author  mnemotron
 * @version 1.0.0
 * @since 2018-01-01
 */
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
