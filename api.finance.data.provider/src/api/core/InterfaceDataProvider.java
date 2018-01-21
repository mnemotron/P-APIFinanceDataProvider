package api.core;

import java.util.Calendar;

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
