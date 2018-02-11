/*
 *  MIT License
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
package api.finance.yahoo.histquote;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.net.URIBuilder;

import api.core.histquote.Interval;
import api.core.http.HttpClient;
import api.core.http.Scheme;
import api.finance.yahoo.histquote.entity.FYBeanHistoricalQuotes;

/**
 * Yahoo Finance Historical Quotes
 * 
 * For example: Request: URL
 * <https://query1.finance.yahoo.com/v8/finance/chart/GOOGL?period1=1486594800&period2=1518217200&interval=1d>
 * Response: JSON
 * 
 * Alternate request: URL <https://query1.finance.yahoo.com/v7/finance/download/GOOGL?period1=1486594800&period2=1518217200&interval=1d&events=history&crumb=X0zfxv/Ju57>
 * Response: CSV
 * 
 * Supported intervals: 1m, 2m, 5m, 15m, 30m, 60m, 90m, 1h, 1d, 5d, 1wk, 1mo, 3mo
 * 
 * Start date (period1) and end date (period2) are UNIX timestamps (seconds since January 1, 1970)
 * Check URL <https://www.epochconverter.com/>
 * 
 * @author mnemotron
 * @version 1.3.0
 * @since 2018-02-10
 */
public class FYHistoricalQuotes
{

	private static final String HOST_SYMBOL_LOOKUP = "query1.finance.yahoo.com";
	private static final String PATH_SYMBOL_LOOKUP = "/v8/finance/chart/";
	private static final String QUERY_START_DATE = "period1";
	private static final String QUERY_END_DATE = "period2";
	private static final String QUERY_INTERVAL = "interval";
	private static final String INTERVAL_DAY_1 = "1d";
	private static final String INTERVAL_MONTH_1 = "1mo";
	private static final String INTERVAL_WEEK_1 = "1wk";

	private Calendar from;
	private Calendar to;
	private Scheme protocol;
	private String tickerID;
	private String interval;

	/**
	 * Default Constructor
	 */
	private FYHistoricalQuotes(String tickerID, Calendar from, Calendar to, Interval interval)
	{
		this.from = from;
		this.to = to;
		this.protocol = Scheme.HTTPS;
		this.tickerID = tickerID;

		this.interval = this.convertInterval(interval);
	}

	/**
	 * Constructor
	 * 
	 * @param proxyHostname
	 *            The proxy hostname
	 * @param proxyPort
	 *            The proxy port
	 */
	private FYHistoricalQuotes(String proxyHostname, int proxyPort, String tickerID, Calendar from, Calendar to, Interval interval)
	{
		this.from = from;
		this.to = to;
		this.protocol = Scheme.HTTPS;
		this.tickerID = tickerID;

		this.interval = this.convertInterval(interval);
	}

	/**
	 * Factory
	 * 
	 * @return Historical quotes instance
	 */
	public static FYHistoricalQuotes FactoryGetInstance(String tickerID, Calendar from, Calendar to, Interval interval)
	{
		FYHistoricalQuotes locHistQuotes = new FYHistoricalQuotes(tickerID, from, to, interval);

		return locHistQuotes;
	}

	/**
	 * Factory
	 * 
	 * @param proxyHostname
	 *            The proxy hostname
	 * @param proxyPort
	 *            The proxy port
	 * @return Historical quotes instance
	 */
	public static FYHistoricalQuotes FactoryGetInstance(String proxyHostname, int proxyPort, String tickerID, Calendar from, Calendar to, Interval interval)
	{
		FYHistoricalQuotes locHistQuotes = new FYHistoricalQuotes(proxyHostname, proxyPort, tickerID, from, to, interval);

		return locHistQuotes;
	}

	/**
	 * Converts the API interval to the Yahoo Finance interval
	 * 
	 * @param interval
	 *            API interval
	 * @return Yahoo Finance interval
	 */
	private String convertInterval(Interval interval)
	{
		String locInterval = new String();

		switch (interval)
		{
		case WEEK_1:
			locInterval = FYHistoricalQuotes.INTERVAL_WEEK_1;
			break;
		case MONTH_1:
			locInterval = FYHistoricalQuotes.INTERVAL_MONTH_1;
			break;
		default:
			locInterval = FYHistoricalQuotes.INTERVAL_DAY_1;
			break;
		}

		return locInterval;
	}

	/**
	 * Builds the URL
	 * 
	 * @return URI
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 */
	private URI buildURI() throws MalformedURLException, URISyntaxException
	{
		URIBuilder locURIBuilder = new URIBuilder();
		locURIBuilder.setScheme(this.protocol.getScheme());
		locURIBuilder.setHost(FYHistoricalQuotes.HOST_SYMBOL_LOOKUP);
		locURIBuilder.setPath(FYHistoricalQuotes.PATH_SYMBOL_LOOKUP + this.tickerID);

		String locStartDate = new String(new Long(from.getTime().getTime() / 1000).toString());
		String locEndDate = new String(new Long(to.getTime().getTime() / 1000).toString());

		locURIBuilder.addParameter(FYHistoricalQuotes.QUERY_START_DATE, locStartDate);
		locURIBuilder.addParameter(FYHistoricalQuotes.QUERY_END_DATE, locEndDate);
		locURIBuilder.addParameter(FYHistoricalQuotes.QUERY_INTERVAL, this.interval);

		return locURIBuilder.build();
	}

	/**
	 * Submits the historical quote request and returns the JSON result.
	 * 
	 * @return JSON
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private String getResponse() throws URISyntaxException, IOException
	{
		String locResponse = new String();

		HttpClient locHttpClient = HttpClient.FactoryGetInstance(this.buildURI());

		// send GET
		locHttpClient.sendGet();

		// return result
		if (locHttpClient.getCode() == HttpStatus.SC_OK)
		{
			locResponse = locHttpClient.getResponse();
		}

		return locResponse;
	}

	/**
	 * Parses and binds the historical quote JSON response.
	 * 
	 * @param response
	 * @return Historical quote response object
	 */
	private FYBeanHistoricalQuotes parseResponse(String response)
	{
		FYBeanHistoricalQuotes locResHistQuotes = new FYBeanHistoricalQuotes();
		
		try
		{
			Jsonb jsonb = JsonbBuilder.create();

			locResHistQuotes = jsonb.fromJson(response, FYBeanHistoricalQuotes.class);
		}
		catch (Exception e)
		{
			// return empty object, if the parsing fails
		}

		return locResHistQuotes;
	}

	/**
	 * Get result
	 * 
	 * @return Historical quote response object
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 */
	public FYBeanHistoricalQuotes getResult() throws URISyntaxException, IOException
	{
		FYBeanHistoricalQuotes locResHistQuotes = new FYBeanHistoricalQuotes();

		String locResponse = this.getResponse();

		if (!locResponse.isEmpty())
		{
			locResHistQuotes = this.parseResponse(locResponse);
		}

		return locResHistQuotes;
	}

	public Calendar getFrom()
	{
		return from;
	}

	public void setFrom(Calendar from)
	{
		this.from = from;
	}

	public Calendar getTo()
	{
		return to;
	}

	public void setTo(Calendar to)
	{
		this.to = to;
	}

	public Scheme getProtocol()
	{
		return protocol;
	}

	public void setProtocol(Scheme protocol)
	{
		this.protocol = protocol;
	}

	public String getTickerID()
	{
		return tickerID;
	}

	public void setTickerID(String tickerID)
	{
		this.tickerID = tickerID;
	}
}
