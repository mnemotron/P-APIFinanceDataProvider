package api.finance.google;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import api.core.InterfaceDataProvider;
import api.core.histquote.Interval;
import api.core.histquote.entity.HistoricalQuote;
import api.core.histquote.entity.HistoricalQuotes;
import api.core.quote.entity.Quote;
import api.core.ticker.entity.Tickers;
import api.finance.google.histquote.HistQuotes;
import api.finance.google.histquote.entity.HistQuote;
import api.finance.google.histquote.entity.ResHistQuotes;

/**
 * API Google Finance - Interface implementation
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-21
 */
public class APIFinanceGoogle implements InterfaceDataProvider {

	@Override
	public Tickers searchTicker(String query) throws Exception {
		return new Tickers();
	}

	@Override
	public Quote getQuote(String tickerID) throws Exception {
		return new Quote();
	}

	@Override
	public HistoricalQuotes getHistoricalQuotes(String tickerID, Calendar from, Calendar to, Interval interval)
			throws Exception {

		HistoricalQuotes locHistoricalQuotes = new HistoricalQuotes();
		ArrayList<HistoricalQuote> locHistoricalQuoteList = new ArrayList<HistoricalQuote>();
		SimpleDateFormat locDateFormatter = new SimpleDateFormat("dd-MMM-yy");

		HistQuotes locHistQuotes = HistQuotes.FactoryGetInstance();

		locHistQuotes.setTickerID(tickerID);
		locHistQuotes.setFrom(from);
		locHistQuotes.setTo(to);

		ResHistQuotes locResHistQuotes = locHistQuotes.getResult();

		// map to result
		locHistoricalQuotes.setTickerID(locResHistQuotes.getTickerID());

		ArrayList<HistQuote> locHistQuote = locResHistQuotes.getHistQuoteList();

		for (HistQuote histQuote : locHistQuote) {
			HistoricalQuote locHistoricalQuote = new HistoricalQuote();
			Calendar locCalendar = null;

			if (histQuote.getDate() != null) {
				try {
					Date locDate = locDateFormatter.parse(histQuote.getDate());
					locCalendar = Calendar.getInstance();
					locCalendar.setTime(locDate);

				} catch (Exception e) {

				}
			}
			
			locHistoricalQuote.setDate(locCalendar);
			locHistoricalQuote.setOpen(histQuote.getOpen());
			locHistoricalQuote.setHigh(histQuote.getHigh());
			locHistoricalQuote.setLow(histQuote.getLow());
			locHistoricalQuote.setClose(histQuote.getClose());
			locHistoricalQuote.setVolume(histQuote.getVolume());

			locHistoricalQuoteList.add(locHistoricalQuote);
		}

		locHistoricalQuotes.setTickerID(tickerID);
		locHistoricalQuotes.setFrom(from);
		locHistoricalQuotes.setTo(to);
		locHistoricalQuotes.setHistoricalQuoteList(locHistoricalQuoteList);

		return locHistoricalQuotes;
	}

}
