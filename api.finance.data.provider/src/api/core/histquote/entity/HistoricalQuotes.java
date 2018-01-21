package api.core.histquote.entity;

import java.util.ArrayList;
import java.util.Calendar;

import api.core.histquote.Interval;

public class HistoricalQuotes
{
	private String tickerID;
	private String tickerName;
	private Interval interval;
	private Calendar from;
	private Calendar to;
	private ArrayList<HistoricalQuote> historicalQuoteList;

	public HistoricalQuotes()
	{
		this.historicalQuoteList = new ArrayList<HistoricalQuote>();
	}

	public ArrayList<HistoricalQuote> getHistoricalQuoteList()
	{
		return historicalQuoteList;
	}

	public void setHistoricalQuoteList(ArrayList<HistoricalQuote> historicalQuoteList)
	{
		this.historicalQuoteList = historicalQuoteList;
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

	public Interval getInterval()
	{
		return interval;
	}

	public void setInterval(Interval interval)
	{
		this.interval = interval;
	}

	public Calendar getFrom()
	{
		return from;
	}

	public void setFrom(Calendar from)
	{
		this.from = from;
	}

	public Calendar getTo()
	{
		return to;
	}

	public void setTo(Calendar to)
	{
		this.to = to;
	}

	public String toString()
	{
		StringBuilder locStringBuilder = new StringBuilder();

		locStringBuilder.append("tickerID:" + this.getTickerID() + "\n");
		locStringBuilder.append("tickerName:" + this.getTickerName() + "\n");
		locStringBuilder.append("interval:" + this.getInterval().getInterval() + "\n");
		locStringBuilder.append("from:" + this.getFrom().toInstant().toString() + "\n");
		locStringBuilder.append("to:" + this.getTo().toInstant().toString() + "\n");

		for (HistoricalQuote historicalQuote : historicalQuoteList)
		{
			locStringBuilder.append(historicalQuote.toString() + "\n");
		}

		return locStringBuilder.toString();
	}
}
