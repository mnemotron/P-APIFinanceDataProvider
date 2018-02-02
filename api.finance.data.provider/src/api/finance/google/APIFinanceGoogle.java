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
package api.finance.google;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
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
		List<Ticker> locTickerList = new ArrayList<Ticker>();

		// call API
		FGSymbolLookup locSL = FGSymbolLookup.FactoryGetInstance();

		locSL.setQuery(query);

		FGResSymbolLookup locFGResSymbolLookup = locSL.getResult();

		// map to result
		List<FGSymbol> locFGSymbolList = locFGResSymbolLookup.getMatches();

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
	public HistoricalQuotes getHistoricalQuotes(String tickerID, Calendar from, Calendar to, Interval interval) throws UnsupportedEncodingException, IOException, URISyntaxException
	{

		HistoricalQuotes locHistoricalQuotes = new HistoricalQuotes();
		ArrayList<HistoricalQuote> locHistoricalQuoteList = new ArrayList<HistoricalQuote>();

		FGHistQuotes locHistQuotes = FGHistQuotes.FactoryGetInstance(tickerID, from, to);

		locHistQuotes.setTickerID(tickerID);

		FGHistoricalQuotes locResHistQuotes = locHistQuotes.getResult();

		if (locResHistQuotes != null)
		{
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
		}

		return locHistoricalQuotes;
	}

}
