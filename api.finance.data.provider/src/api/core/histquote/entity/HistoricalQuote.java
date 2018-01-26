package api.core.histquote.entity;

import java.util.Calendar;

public class HistoricalQuote
{
	private Calendar date;
	private double open;
	private double close;
	private double high;
	private double low;
	private long volume;

	public HistoricalQuote()
	{

	}

	public Calendar getDate()
	{
		return date;
	}

	public void setDate(Calendar date)
	{
		this.date = date;
	}

	public double getOpen()
	{
		return open;
	}

	public void setOpen(double open)
	{
		this.open = open;
	}

	public double getClose()
	{
		return close;
	}

	public void setClose(double close)
	{
		this.close = close;
	}

	public double getHigh()
	{
		return high;
	}

	public void setHigh(double high)
	{
		this.high = high;
	}

	public double getLow()
	{
		return low;
	}

	public void setLow(double low)
	{
		this.low = low;
	}

	public long getVolume()
	{
		return volume;
	}

	public void setVolume(long volume)
	{
		this.volume = volume;
	}
	
	public String toString()
	{
		String locDate = new String();
		
		if (this.date != null)
		{
			locDate = this.date.toInstant().toString();
		}
		else
		{
			locDate = "null";
		}
		
		return new String("{ " + "date:" + locDate + ", " + "close:" + this.close + ", " + "open:" + this.open + ", " + "low:" + this.low + ", " + "high:" + this.high + ", " + "volume:" + this.volume + " }");
	}
}
