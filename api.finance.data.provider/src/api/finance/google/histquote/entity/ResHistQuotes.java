package api.finance.google.histquote.entity;

import java.util.ArrayList;
import java.util.Calendar;

public class ResHistQuotes {

	private String tickerID;
	private Calendar from;
	private Calendar to;
	private ArrayList<HistQuote> histQuoteList = new ArrayList<HistQuote>();

	public ResHistQuotes() {

	}

	public String getTickerID() {
		return tickerID;
	}

	public void setTickerID(String tickerID) {
		this.tickerID = tickerID;
	}

	public Calendar getFrom() {
		return from;
	}

	public void setFrom(Calendar from) {
		this.from = from;
	}

	public Calendar getTo() {
		return to;
	}

	public void setTo(Calendar to) {
		this.to = to;
	}

	public ArrayList<HistQuote> getHistQuoteList() {
		return histQuoteList;
	}

	public void setHistQuoteList(ArrayList<HistQuote> histQuoteList) {
		this.histQuoteList = histQuoteList;
	}
}
