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
package api.finance.stooq;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import api.core.InterfaceDataProvider;
import api.core.histquote.Interval;
import api.core.histquote.entity.HistoricalQuote;
import api.core.histquote.entity.HistoricalQuotes;
import api.core.quote.entity.Quote;
import api.core.ticker.entity.Tickers;
import api.core.util.ParseToNumber;
import api.finance.stooq.histquote.FSHistoricalQuotes;
import api.finance.stooq.histquote.entity.FSBeanHistoricalQuote;
import api.finance.stooq.histquote.entity.FSBeanHistoricalQuotes;
import api.finance.stooq.symbol.FSSymbolLookup;

/**
 * API Stooq - Interface implementation
 * 
 * @author mnemotron
 * @version 1.2.0
 * @since 2018-02-03
 */
public class APIFinanceStooq implements InterfaceDataProvider
{

	@Override
	public Tickers searchTicker(String query) throws Exception
	{
		FSSymbolLookup locSymbolLookup = FSSymbolLookup.FactoryGetInstance();
		
		locSymbolLookup.setQuery(query);
		
		return locSymbolLookup.getResult();
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
		List<HistoricalQuote> locHistoricalQuoteList = new ArrayList<HistoricalQuote>();

		FSHistoricalQuotes locHistQuotes = FSHistoricalQuotes.FactoryGetInstance(tickerID, from, to, interval);

		locHistQuotes.setTickerID(tickerID);

		FSBeanHistoricalQuotes locResHistQuotes = locHistQuotes.getResult();

		// map to result
		locHistoricalQuotes.setTickerID(locResHistQuotes.getTickerID());

		List<FSBeanHistoricalQuote> locHistQuote = locResHistQuotes.getHistQuoteList();

		for (FSBeanHistoricalQuote histQuote : locHistQuote)
		{
			HistoricalQuote locHistoricalQuote = new HistoricalQuote();
			Calendar locCalendar = null;

			if (histQuote.getDate() != null)
			{
				try
				{
					locCalendar = Calendar.getInstance();
					locCalendar.setTime(histQuote.getDate());
				}
				catch (Exception e)
				{

				}
			}

			locHistoricalQuote.setDate(locCalendar);
			locHistoricalQuote.setOpen(ParseToNumber.parseStringToDouble(histQuote.getOpen()));
			locHistoricalQuote.setHigh(ParseToNumber.parseStringToDouble(histQuote.getHigh()));
			locHistoricalQuote.setLow(ParseToNumber.parseStringToDouble(histQuote.getLow()));
			locHistoricalQuote.setClose(ParseToNumber.parseStringToDouble(histQuote.getClose()));
			locHistoricalQuote.setVolume(ParseToNumber.parseStringToLong(histQuote.getVolume()));

			locHistoricalQuoteList.add(locHistoricalQuote);
		}

		locHistoricalQuotes.setTickerID(tickerID);
		locHistoricalQuotes.setFrom(from);
		locHistoricalQuotes.setTo(to);
		locHistoricalQuotes.setInterval(interval);
		locHistoricalQuotes.setHistoricalQuoteList(locHistoricalQuoteList);

		return locHistoricalQuotes;
	}

}
