package api.finance.yahoo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import api.core.InterfaceDataProvider;
import api.core.histquote.Interval;
import api.core.histquote.entity.HistoricalQuotes;
import api.core.quote.entity.Quote;
import api.core.ticker.entity.Ticker;
import api.core.ticker.entity.Tickers;
import api.finance.yahoo.symbol.SymbolLookup;
import api.finance.yahoo.symbol.entity.ResSymbolLookup;
import api.finance.yahoo.symbol.entity.ResultSet;
import api.finance.yahoo.symbol.entity.Symbol;

/**
 * API Yahoo Finance - Interface implementation
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public class APIFinanceYahoo implements InterfaceDataProvider
{

	@Override
	public Quote getQuote(String tickerID)
	{
		return new Quote();
	}

	@Override
	public Tickers searchTicker(String query) throws Exception
	{
		Tickers locTickers = new Tickers();
		ArrayList<Ticker> locTickerList = new ArrayList<Ticker>();

		// call API
		SymbolLookup locSL = SymbolLookup.FactoryGetInstance();

		locSL.setQuery(query);
		// TODO Language, Region
		locSL.setRegion("EU");
		locSL.setLanguage("de-DE");

		ResSymbolLookup locResSymbolLookup = locSL.getResult();

		// map to result
		ResultSet locResultSet = locResSymbolLookup.getResultset();

		locTickers.setQuery(locResultSet.getQuery());

		Iterator<Symbol> locResultIterator = locResultSet.getResult().iterator();

		while (locResultIterator.hasNext())
		{
			Ticker locTicker = new Ticker();

			Symbol locSymbol = locResultIterator.next();

			if (locSymbol == null)
			{
				continue;
			}

			locTicker.setTickerID(locSymbol.getSymbol());
			locTicker.setTickerName(locSymbol.getName());

			locTickerList.add(locTicker);
		}

		locTickers.setTickerList(locTickerList);

		return locTickers;
	}

	@Override
	public HistoricalQuotes getHistoricalQuotes(String tickerID, Calendar from, Calendar to, Interval interval)
	{
		return new HistoricalQuotes();
	}
}
