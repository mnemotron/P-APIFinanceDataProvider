package api.core.histquote.entity;

import java.util.ArrayList;
import java.util.Calendar;

import api.core.histquote.Interval;

public class HistoricalQuotes
{
	private String tickerID;
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

		if(this.tickerID != null)
		{
			locStringBuilder.append("tickerID:" + this.getTickerID() + "\n");
		}
		else
		{
			locStringBuilder.append("tickerID:" + "null" + "\n");
		}
		
		if(this.interval!= null)
		{
			locStringBuilder.append("interval:" + this.getInterval().getInterval() + "\n");
		}
		else
		{
			locStringBuilder.append("interval:" + "null" + "\n");
		}
		
		if(this.from != null)
		{
			locStringBuilder.append("from:" + this.getFrom().toInstant().toString() + "\n");
		}
		else
		{
			locStringBuilder.append("from:" + "null" + "\n");
		}
		
		if(this.to != null)
		{
			locStringBuilder.append("to:" + this.getTo().toInstant().toString() + "\n");	
		}
		else
		{
			locStringBuilder.append("to:" + "null" + "\n");
		}


		for (HistoricalQuote historicalQuote : historicalQuoteList)
		{
			locStringBuilder.append(historicalQuote.toString() + "\n");
		}

		return locStringBuilder.toString();
	}
}
