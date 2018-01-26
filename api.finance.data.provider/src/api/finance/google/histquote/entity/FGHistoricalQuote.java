package api.finance.google.histquote.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import api.core.ConvertValueToDate;

public class FGHistoricalQuote {

	@CsvCustomBindByName(converter = ConvertValueToDate.class)
	private java.util.Date Date;
	
	@CsvBindByName(locale = "en")
	private double Open;
	
	@CsvBindByName(locale = "en")
	private double High;
	
	@CsvBindByName(locale = "en")
	private double Low;
	
	@CsvBindByName(locale = "en")
	private double Close;
	
	@CsvBindByName
	private long Volume;

	public java.util.Date getDate()
	{
		return Date;
	}

	public void setDate(java.util.Date date)
	{
		Date = date;
	}

	public double getOpen()
	{
		return Open;
	}

	public void setOpen(double open)
	{
		Open = open;
	}

	public double getHigh()
	{
		return High;
	}

	public void setHigh(double high)
	{
		High = high;
	}

	public double getLow()
	{
		return Low;
	}

	public void setLow(double low)
	{
		Low = low;
	}

	public double getClose()
	{
		return Close;
	}

	public void setClose(double close)
	{
		Close = close;
	}

	public long getVolume()
	{
		return Volume;
	}

	public void setVolume(long volume)
	{
		Volume = volume;
	}
}