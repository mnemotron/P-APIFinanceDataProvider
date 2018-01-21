package api;

import java.util.Calendar;

import api.core.API;
import api.core.InterfaceDataProvider;
import api.core.histquote.Interval;
import api.core.histquote.entity.HistoricalQuotes;
import api.core.quote.entity.Quote;
import api.core.ticker.entity.Tickers;
import api.finance.simulation.APIFinanceSimulation;
import api.finance.yahoo.APIFinanceYahoo;

/**
 * API Manager
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public class APIManager implements InterfaceDataProvider
{
	private API api;
	private InterfaceDataProvider dataProvider;

	public static APIManager FactoryGetInstance(API api)
	{
		return new APIManager(api);
	}

	private APIManager(API api)
	{
		this.api = api;

		this.createAPIInstance();
	}

	private void createAPIInstance()
	{
		switch (this.api)
		{
		case YAHOO_FINANCE:
			this.dataProvider = new APIFinanceYahoo();
			break;

		case SIMULATION:
			this.dataProvider = new APIFinanceSimulation();
			break;

		default:
			this.dataProvider = new APIFinanceSimulation();
			break;
		}
	}

	@Override
	public Tickers searchTicker(String query) throws Exception
	{	
		return this.dataProvider.searchTicker(query);
	}

	@Override
	public Quote getQuote(String tickerID) throws Exception
	{
		return this.dataProvider.getQuote(tickerID);
	}

	@Override
	public HistoricalQuotes getHistoricalQuotes(String tickerID, Calendar from, Calendar to, Interval interval) throws Exception
	{
		return this.dataProvider.getHistoricalQuotes(tickerID, from, to, interval);
	}

}
