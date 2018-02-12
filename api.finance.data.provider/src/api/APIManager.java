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
package api;

import java.util.Calendar;
import java.util.Map;

import api.core.API;
import api.core.InterfaceDataProvider;
import api.core.histquote.Interval;
import api.core.histquote.entity.HistoricalQuotes;
import api.core.quote.entity.Quote;
import api.core.ticker.entity.Tickers;
import api.finance.google.APIFinanceGoogle;
import api.finance.simulation.APIFinanceSimulation;
import api.finance.stooq.APIFinanceStooq;
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

		case GOOGLE_FINANCE:
			this.dataProvider = new APIFinanceGoogle();
			break;
			
		case STOOQ:
			this.dataProvider = new APIFinanceStooq();
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

	@Override
	public Map<String, HistoricalQuotes> getHistoricalQuoteList() throws Exception
	{
		return null;
	}

}
