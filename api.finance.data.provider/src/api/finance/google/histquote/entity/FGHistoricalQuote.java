package api.finance.google.histquote.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.util.Date;

public class FGHistoricalQuote {

	@CsvBindByName(locale = "en")
    @CsvDate("dd-MMM-yy")
	private Date date;
	
	@CsvBindByName(locale = "en")
	private double open;
	
	@CsvBindByName(locale = "en")
	private double high;
	
	@CsvBindByName(locale = "en")
	private double low;
	
	@CsvBindByName(locale = "en")
	private double close;
	
	@CsvBindByName
	private long volume;

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
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

	public double getClose()
	{
		return close;
	}

	public void setClose(double close)
	{
		this.close = close;
	}

	public long getVolume()
	{
		return volume;
	}

	public void setVolume(long volume)
	{
		this.volume = volume;
	}
}