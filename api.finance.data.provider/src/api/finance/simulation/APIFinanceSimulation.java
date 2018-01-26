package api.finance.simulation;

import java.util.ArrayList;
import java.util.Calendar;
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
		ArrayList<HistoricalQuote> locHistoricalQuoteList = new ArrayList<HistoricalQuote>();
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

}
