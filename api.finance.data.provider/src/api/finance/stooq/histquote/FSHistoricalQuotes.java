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
package api.finance.stooq.histquote;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.net.URIBuilder;

import com.opencsv.bean.CsvToBeanBuilder;

import api.core.histquote.Interval;
import api.core.http.HttpClient;
import api.core.http.Scheme;
import api.finance.stooq.histquote.entity.FSBeanHistoricalQuote;
import api.finance.stooq.histquote.entity.FSBeanHistoricalQuotes;

/**
 * Stooq Historical Quotes
 * 
 * For example: 
 * Request: URL <https://stooq.com/q/d/l/?s=googl.us&d1=20100819&d2=2018020&i=d>
 * Response: CSV
 * 
 * @author mnemotron
 * @version 1.2.0
 * @since 2018-02-03
 */
public class FSHistoricalQuotes
{
	private static final String HOST_SYMBOL_LOOKUP = "stooq.com";
	private static final String PATH_SYMBOL_LOOKUP = "/q/d/l";
	private static final String QUERY_TICKERID = "s";
	private static final String QUERY_START_DATE = "d1";
	private static final String QUERY_END_DATE = "d2";
	private static final String QUERY_INTERVAL = "i";
	private static final String QUERY_INTERVAL_DAYLY = "d";
	private static final String QUERY_INTERVAL_WEEKLY = "w";
	private static final String QUERY_INTERVAL_MONTHLY = "m";
	private static final String QUERY_INTERVAL_QUARTERLY = "q";
	private static final String QUERY_INTERVAL_YEARLY = "y";

	private Calendar from;
	private Calendar to;
	private Interval interval;
	private Scheme protocol;
	private String tickerID;

	/**
	 * Default Constructor
	 */
	private FSHistoricalQuotes(String tickerID, Calendar from, Calendar to, Interval interval)
	{
		this.from = from;
		this.to = to;
		this.interval = interval;
		this.protocol = Scheme.HTTPS;
		this.tickerID = tickerID;
	}

	/**
	 * Factory
	 * 
	 * @return Historical quotes instance
	 */
	public static FSHistoricalQuotes FactoryGetInstance(String tickerID, Calendar from, Calendar to, Interval interval)
	{
		FSHistoricalQuotes locHistQuotes = new FSHistoricalQuotes(tickerID, from, to, interval);

		return locHistQuotes;
	}

	/**
	 * Submits the historical quote request and returns the CSV result.
	 * 
	 * @return CSV
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
	 * Parses and binds the historical quote CSV response.
	 * 
	 * @param response
	 * @return Historical quote response object
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private FSBeanHistoricalQuotes parseResponse(String response) throws UnsupportedEncodingException, IOException
	{
		FSBeanHistoricalQuotes locHistQuotes = new FSBeanHistoricalQuotes();

		InputStreamReader locReaderBOM = new InputStreamReader(new BOMInputStream(IOUtils.toInputStream(response, StandardCharsets.UTF_8.name())), StandardCharsets.UTF_8.name());

		String locResponseWithoutBOM = IOUtils.toString(locReaderBOM);

		locReaderBOM.close();

		List<FSBeanHistoricalQuote> locHistQuoteList = new CsvToBeanBuilder<FSBeanHistoricalQuote>(new StringReader(locResponseWithoutBOM)).withType(FSBeanHistoricalQuote.class).build().parse();

		locHistQuotes.setHistQuoteList(new ArrayList<FSBeanHistoricalQuote>(locHistQuoteList));

		return locHistQuotes;
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
		locURIBuilder.setHost(FSHistoricalQuotes.HOST_SYMBOL_LOOKUP);
		locURIBuilder.setPath(FSHistoricalQuotes.PATH_SYMBOL_LOOKUP);
		locURIBuilder.addParameter(FSHistoricalQuotes.QUERY_TICKERID, this.tickerID);

		DateFormat locDateFormat = new SimpleDateFormat("yyyyMMdd");
		String locStartDate = locDateFormat.format(this.from.getTime());
		String locEndDate = locDateFormat.format(this.to.getTime());

		locURIBuilder.addParameter(FSHistoricalQuotes.QUERY_START_DATE, locStartDate);
		locURIBuilder.addParameter(FSHistoricalQuotes.QUERY_END_DATE, locEndDate);

		String locInterval = new String();

		switch (this.interval)
		{
		case WEEK_1:
			locInterval = FSHistoricalQuotes.QUERY_INTERVAL_WEEKLY;
			break;
		case MONTH_1:
			locInterval = FSHistoricalQuotes.QUERY_INTERVAL_MONTHLY;
			break;
		case QUARTER_1:
			locInterval = FSHistoricalQuotes.QUERY_INTERVAL_QUARTERLY;
			break;
		case YEAR_1:
			locInterval = FSHistoricalQuotes.QUERY_INTERVAL_YEARLY;
			break;
		default:
			locInterval = FSHistoricalQuotes.QUERY_INTERVAL_DAYLY;
			break;
		}

		locURIBuilder.addParameter(FSHistoricalQuotes.QUERY_INTERVAL, locInterval);

		return locURIBuilder.build();
	}
	
	/**
	 * Get result
	 * 
	 * @return Historical quote response object
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 */
	public FSBeanHistoricalQuotes getResult() throws UnsupportedEncodingException, IOException, URISyntaxException
	{
		FSBeanHistoricalQuotes locHistQuotes = new FSBeanHistoricalQuotes();

		String locResponse = this.getResponse();

		if (!locResponse.isEmpty())
		{
			locHistQuotes = this.parseResponse(locResponse);
		}

		return locHistQuotes;
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

	public Interval getInterval()
	{
		return interval;
	}

	public void setInterval(Interval interval)
	{
		this.interval = interval;
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
