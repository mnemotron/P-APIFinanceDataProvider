package api.finance.google.histquote.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class HistQuote {

	@CsvBindByName
	@CsvDate("dd-MMM-yy")
	private java.util.Date Date;

	@CsvBindByName
	private double Open;

	@CsvBindByName
	private double High;

	@CsvBindByName
	private double Low;

	@CsvBindByName
	private double Close;

	@CsvBindByName
	private double Volume;

	public HistQuote()
	{
		
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

	public double getVolume() {
		return Volume;
	}

	public void setVolume(double volume) {
		Volume = volume;
	}
}
