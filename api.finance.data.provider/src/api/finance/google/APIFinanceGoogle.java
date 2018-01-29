package api.finance.google;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import api.core.InterfaceDataProvider;
import api.core.histquote.Interval;
import api.core.histquote.entity.HistoricalQuote;
import api.core.histquote.entity.HistoricalQuotes;
import api.core.quote.entity.Quote;
import api.core.ticker.entity.Ticker;
import api.core.ticker.entity.Tickers;
import api.finance.google.histquote.FGHistQuotes;
import api.finance.google.histquote.entity.FGHistoricalQuote;
import api.finance.google.histquote.entity.FGHistoricalQuotes;
import api.finance.google.symbol.FGSymbolLookup;
import api.finance.google.symbol.entity.FGResSymbolLookup;
import api.finance.google.symbol.entity.FGSymbol;

/**
 * API Google Finance - Interface implementation
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-21
 */
public class APIFinanceGoogle implements InterfaceDataProvider
{

	@Override
	public Tickers searchTicker(String query) throws Exception 
	{
		Tickers locTickers = new Tickers();
		ArrayList<Ticker> locTickerList = new ArrayList<Ticker>();

		// call API
		FGSymbolLookup locSL = FGSymbolLookup.FactoryGetInstance();

		locSL.setQuery(query);

		FGResSymbolLookup locFGResSymbolLookup = locSL.getResult();

		// map to result
		ArrayList<FGSymbol> locFGSymbolList = locFGResSymbolLookup.getMatches();

		locTickers.setQuery(query);

		Iterator<FGSymbol> locIterator = locFGSymbolList.iterator();

		while (locIterator.hasNext())
		{
			Ticker locTicker = new Ticker();

			FGSymbol locFGSymbol = locIterator.next();

			if (locFGSymbol == null)
			{
				continue;
			}

			locTicker.setTickerID(locFGSymbol.getT());
			locTicker.setTickerName(locFGSymbol.getN());

			locTickerList.add(locTicker);
		}

		locTickers.setTickerList(locTickerList);

		return locTickers;
	}

	@Override
	public Quote getQuote(String tickerID) throws Exception
	{
		return new Quote();
	}

	@Override
	public HistoricalQuotes getHistoricalQuotes(String tickerID, Calendar from, Calendar to, Interval interval) throws Exception
	{

		HistoricalQuotes locHistoricalQuotes = new HistoricalQuotes();
		ArrayList<HistoricalQuote> locHistoricalQuoteList = new ArrayList<HistoricalQuote>();
		SimpleDateFormat locDateFormatter = new SimpleDateFormat("dd-MMM-yy");

		FGHistQuotes locHistQuotes = FGHistQuotes.FactoryGetInstance();

		locHistQuotes.setTickerID(tickerID);
		locHistQuotes.setFrom(from);
		locHistQuotes.setTo(to);

		FGHistoricalQuotes locResHistQuotes = locHistQuotes.getResult();

		// map to result
		locHistoricalQuotes.setTickerID(locResHistQuotes.getTickerID());

		ArrayList<FGHistoricalQuote> locHistQuote = locResHistQuotes.getHistQuoteList();

		for (FGHistoricalQuote histQuote : locHistQuote)
		{
			HistoricalQuote locHistoricalQuote = new HistoricalQuote();
			Calendar locCalendar = null;

			if (histQuote.getDate() != null)
			{
				try
				{
					// Date locDate =
					// locDateFormatter.parse(histQuote.getDate());
					locCalendar = Calendar.getInstance();
					// locCalendar.setTime(locDate);
					locCalendar.setTime(histQuote.getDate());

				}
				catch (Exception e)
				{

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
