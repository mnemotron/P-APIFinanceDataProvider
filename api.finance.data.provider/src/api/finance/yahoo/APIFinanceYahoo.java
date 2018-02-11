/*
 * MIT License
 *
 * Copyright (c) 2018 mnemotron
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package api.finance.yahoo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import api.core.InterfaceDataProvider;
import api.core.histquote.Interval;
import api.core.histquote.entity.HistoricalQuote;
import api.core.histquote.entity.HistoricalQuotes;
import api.core.quote.entity.Quote;
import api.core.ticker.entity.Ticker;
import api.core.ticker.entity.Tickers;
import api.finance.yahoo.histquote.FYHistoricalQuotes;
import api.finance.yahoo.histquote.entity.FYBeanHistoricalQuotes;
import api.finance.yahoo.symbol.FYSymbolLookup;
import api.finance.yahoo.symbol.entity.FYBeanSymbolLookup;
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
		FYSymbolLookup locSL = FYSymbolLookup.FactoryGetInstance();

		locSL.setQuery(query);
		// TODO Language, Region
		locSL.setRegion("EU");
		locSL.setLanguage("de-DE");

		FYBeanSymbolLookup locResSymbolLookup = locSL.getResult();

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
	public HistoricalQuotes getHistoricalQuotes(String tickerID, Calendar from, Calendar to, Interval interval) throws Exception
	{
		HistoricalQuotes locHistoricalQuotes = new HistoricalQuotes();
		List<HistoricalQuote> locHistoricalQuoteList = new ArrayList<HistoricalQuote>();
		
		FYHistoricalQuotes locHistQuotes = FYHistoricalQuotes.FactoryGetInstance(tickerID, from, to, interval);
		
		FYBeanHistoricalQuotes locBeanHistQuotes = locHistQuotes.getResult();
		
//		locBeanHistQuotes.getChart(). 
		
		locHistoricalQuotes.setTickerID(tickerID);
		locHistoricalQuotes.setFrom(from);
		locHistoricalQuotes.setTo(to);
		locHistoricalQuotes.setHistoricalQuoteList(locHistoricalQuoteList);
		
		return locHistoricalQuotes;
	}
}
