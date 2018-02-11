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
package api.core;

import java.util.Calendar;
import java.util.Map;

import api.core.histquote.Interval;
import api.core.histquote.TimePeriod;
import api.core.histquote.entity.HistoricalQuotes;
import api.core.quote.entity.Quote;
import api.core.ticker.entity.Tickers;

/**
 * Finance Data Provider Default API Interface
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public interface InterfaceDataProvider
{
	/**
	 * Search ticker ID
	 * 
	 * @param query Search string
	 * @return Ticker search result
	 */
	public Tickers searchTicker(String query) throws Exception;

	/**
	 * Get quote
	 * 
	 * @param tickerID Ticker ID
	 * @return The quote of the ticker ID
	 */
	public Quote getQuote(String tickerID) throws Exception;
	
	/**
	 * Get historical quotes from different ticker IDs
	 * 
	 * @return Hash map key: ticker ID, value: BEAN historical quotes
	 */
	public Map<String, HistoricalQuotes> getHistoricalQuoteList() throws Exception;

	/**
	 * Get historical quotes
	 * 
	 * @param tickerID Ticker ID
	 * @param from From date
	 * @param to To date
	 * @param interval Interval
	 * @return Historical quote data of the ticker ID
	 */
	public HistoricalQuotes getHistoricalQuotes(String tickerID, Calendar from, Calendar to, Interval interval) throws Exception;

	/**
	 * Get historical quotes
	 * 
	 * @param tickerID Ticker ID
	 * @param timePeriod Time period
	 * @param interval Interval
	 * @return Historical quote data of the ticker ID
	 */
	public default HistoricalQuotes getHistoricalQuotes(String tickerID, TimePeriod timePeriod, Interval interval) throws Exception
	{
		Calendar locFrom = Calendar.getInstance();
		Calendar locTo = Calendar.getInstance();

		switch (timePeriod)
		{
		case DAY_1:
			locFrom.add(Calendar.DAY_OF_MONTH, -1);
			break;
		case WEEK_1:
			locFrom.add(Calendar.WEEK_OF_MONTH, -1);
			break;
		case MONTH_1:
			locFrom.add(Calendar.MONTH, -1);
			break;
		case MONTH_6:
			locFrom.add(Calendar.MONTH, -6);
			break;
		case YEAR_1:
			locFrom.add(Calendar.YEAR, -1);
			break;
		case YEAR_3:
			locFrom.add(Calendar.YEAR, -3);
			break;
		case YEAR_5:
			locFrom.add(Calendar.YEAR, -5);
			break;
		default:
			locFrom.add(Calendar.YEAR, -1);
			break;
		}

		return getHistoricalQuotes(tickerID, locFrom, locTo, interval);
	}
}
