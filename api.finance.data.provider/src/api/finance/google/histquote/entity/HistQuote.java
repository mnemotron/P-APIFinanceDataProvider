package api.finance.google.histquote.entity;

import com.opencsv.bean.CsvBindByName;

public class HistQuote {

	@CsvBindByName
	private String Date;
	
	@CsvBindByName
	private double Open;
	
	@CsvBindByName
	private double High;
	
	@CsvBindByName
	private double Low;
	
	@CsvBindByName
	private double Close;
	
	@CsvBindByName
	private long Volume;

	public HistQuote()
	{
		
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public double getOpen() {
		return Open;
	}

	public void setOpen(double open) {
		Open = open;
	}

	public double getHigh() {
		return High;
	}

	public void setHigh(double high) {
		High = high;
	}

	public double getLow() {
		return Low;
	}

	public void setLow(double low) {
		Low = low;
	}

	public double getClose() {
		return Close;
	}

	public void setClose(double close) {
		Close = close;
	}

	public long getVolume() {
		return Volume;
	}

	public void setVolume(long volume) {
		Volume = volume;
	}
}