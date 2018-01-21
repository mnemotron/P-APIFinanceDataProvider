package api.core.ticker.entity;

/**
 * Ticker
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public class Ticker
{
	private String tickerID;
	private String tickerName;

	public Ticker()
	{

	}

	public String getTickerID()
	{
		return tickerID;
	}

	public void setTickerID(String tickerID)
	{
		this.tickerID = tickerID;
	}

	public String getTickerName()
	{
		return tickerName;
	}

	public void setTickerName(String tickerName)
	{
		this.tickerName = tickerName;
	}
	
	public String toString()
	{
		return new String("{ " + "tickerID:" + this.tickerID + ", " + "tickerName:" + this.tickerName + " }");
	}

}
