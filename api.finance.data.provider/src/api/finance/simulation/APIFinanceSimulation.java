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
package api.finance.simulation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import api.core.InterfaceDataProvider;
import api.core.histquote.Interval;
import api.core.histquote.entity.HistoricalQuote;
import api.core.histquote.entity.HistoricalQuotes;
import api.core.quote.entity.Quote;
import api.core.ticker.entity.Tickers;

/**
 * API Finance Simulation - Interface implementation
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public class APIFinanceSimulation implements InterfaceDataProvider
{

	@Override
	public Tickers searchTicker(String query) throws Exception
	{
		return new Tickers();
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
		Random locRandom = new Random();

		Calendar locFrom = (Calendar) from.clone();

		while (locFrom.before(to))
		{
			HistoricalQuote locHistoricalQuote = new HistoricalQuote();

			locHistoricalQuote.setDate((Calendar) locFrom.clone());
			locHistoricalQuote.setClose(locRandom.nextDouble());

			locHistoricalQuoteList.add(locHistoricalQuote);

			// next interval
			switch (interval)
			{
			case DAY_1:
				locFrom.add(Calendar.DAY_OF_MONTH, 1);
				break;
			case WEEK_1:
				locFrom.add(Calendar.WEEK_OF_MONTH, 1);
				break;
			case MONTH_1:
				locFrom.add(Calendar.MONTH, 1);
				break;
			default:
				locFrom.add(Calendar.DAY_OF_MONTH, 1);
				break;
			}
		}

		locHistoricalQuotes.setTickerID(tickerID);
		locHistoricalQuotes.setFrom(from);
		locHistoricalQuotes.setTo(to);
		locHistoricalQuotes.setInterval(interval);
		locHistoricalQuotes.setHistoricalQuoteList(locHistoricalQuoteList);

		return locHistoricalQuotes;
	}

	@Override
	public Map<String, HistoricalQuotes> getHistoricalQuoteList() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

}
