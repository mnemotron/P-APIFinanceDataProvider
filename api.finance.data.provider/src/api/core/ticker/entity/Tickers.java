package api.core.ticker.entity;

import java.util.ArrayList;

public class Tickers
{
	private String query;
	private ArrayList<Ticker> tickerList;

	public Tickers()
	{
		this.tickerList = new ArrayList<Ticker>();
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public ArrayList<Ticker> getTickerList()
	{
		return tickerList;
	}

	public void setTickerList(ArrayList<Ticker> tickerList)
	{
		this.tickerList = tickerList;
	}

	public String toString()
	{
		StringBuilder locStringBuilder = new StringBuilder();

		locStringBuilder.append("query:" + this.getQuery() + "\n");

		for (Ticker ticker : tickerList)
		{
			locStringBuilder.append(ticker.toString() + "\n");
		}

		return locStringBuilder.toString();
	}
}
