package api.finance.google;

import java.util.ArrayList;
import java.util.Calendar;

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
			

		}
		
//		locHistoricalQuotes.setHistoricalQuoteList();
		
		
		return locHistoricalQuotes;
	}

}
