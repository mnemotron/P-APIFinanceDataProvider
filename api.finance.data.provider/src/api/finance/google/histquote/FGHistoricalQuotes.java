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
package api.finance.google.histquote;

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

import api.core.http.HttpClient;
import api.core.http.Scheme;
import api.finance.google.histquote.entity.FGBeanHistoricalQuote;
import api.finance.google.histquote.entity.FGBeanHistoricalQuotes;

/**
 * Google Finance Historical Quotes
 * 
 * For example: Request: URL
 * <https://finance.google.com/finance/historical?q=GOOGL&startdate=Jan+1%2C+2010&enddate=Jan+1%2C+2018&output=csv>
 * Response: CSV
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-22
 */
public class FGHistoricalQuotes
{

	private static final String HOST_SYMBOL_LOOKUP = "finance.google.com";
	private static final String PATH_SYMBOL_LOOKUP = "/finance/historical";
	private static final String QUERY_TICKERID = "q";
	private static final String QUERY_START_DATE = "startdate";
	private static final String QUERY_END_DATE = "enddate";
	private static final String QUERY_OUTPUT = "output";
	private static final String VALUE_OUTPUT_CSV = "csv";

	private Calendar from;
	private Calendar to;
	private Scheme protocol;
	private String tickerID;

	/**
	 * Default Constructor
	 */
	private FGHistoricalQuotes(String tickerID, Calendar from, Calendar to)
	{
		this.from = from;
		this.to = to;
		this.protocol = Scheme.HTTPS;
		this.tickerID = tickerID;
	}

	/**
	 * Constructor
	 * 
	 * @param proxyHostname
	 *            The proxy hostname
	 * @param proxyPort
	 *            The proxy port
	 */
	private FGHistoricalQuotes(String proxyHostname, int proxyPort, String tickerID, Calendar from, Calendar to)
	{
		this.from = from;
		this.to = to;
		this.protocol = Scheme.HTTPS;
		this.tickerID = tickerID;
	}

	/**
	 * Factory
	 * 
	 * @return Historical quotes instance
	 */
	public static FGHistoricalQuotes FactoryGetInstance(String tickerID, Calendar from, Calendar to)
	{
		FGHistoricalQuotes locHistQuotes = new FGHistoricalQuotes(tickerID, from, to);

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
	public static FGHistoricalQuotes FactoryGetInstance(String proxyHostname, int proxyPort, String tickerID, Calendar from, Calendar to)
	{
		FGHistoricalQuotes locHistQuotes = new FGHistoricalQuotes(proxyHostname, proxyPort, tickerID, from, to);

		return locHistQuotes;
	}

	/**
	 * Builds the URL
	 * 
	 * @return URL
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 */
	private URI buildURI() throws MalformedURLException, URISyntaxException
	{
		URIBuilder locURIBuilder = new URIBuilder();
		locURIBuilder.setScheme(this.protocol.getScheme());
		locURIBuilder.setHost(FGHistoricalQuotes.HOST_SYMBOL_LOOKUP);
		locURIBuilder.setPath(FGHistoricalQuotes.PATH_SYMBOL_LOOKUP);
		locURIBuilder.addParameter(FGHistoricalQuotes.QUERY_TICKERID, this.tickerID);

		// TODO Google Query Date Format
		DateFormat locDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String locStartDate = locDateFormat.format(this.from.getTime());
		String locEndDate = locDateFormat.format(this.to.getTime());

		locURIBuilder.addParameter(FGHistoricalQuotes.QUERY_START_DATE, locStartDate);
		locURIBuilder.addParameter(FGHistoricalQuotes.QUERY_END_DATE, locEndDate);
		locURIBuilder.addParameter(FGHistoricalQuotes.QUERY_OUTPUT, VALUE_OUTPUT_CSV);

		return locURIBuilder.build();
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
	 * @param CSV
	 * @return Historical quote response object
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private FGBeanHistoricalQuotes parseResponse(String response) throws UnsupportedEncodingException, IOException
	{
		FGBeanHistoricalQuotes locResHistQuotes = new FGBeanHistoricalQuotes();

		InputStreamReader locReaderBOM = new InputStreamReader(new BOMInputStream(IOUtils.toInputStream(response, StandardCharsets.UTF_8.name())), StandardCharsets.UTF_8.name());

		String locResponseWithoutBOM = IOUtils.toString(locReaderBOM);

		locReaderBOM.close();

		List<FGBeanHistoricalQuote> locHistQuoteList = new CsvToBeanBuilder<FGBeanHistoricalQuote>(new StringReader(locResponseWithoutBOM)).withType(FGBeanHistoricalQuote.class).build().parse();

		locResHistQuotes.setHistQuoteList(new ArrayList<FGBeanHistoricalQuote>(locHistQuoteList));

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
	public FGBeanHistoricalQuotes getResult() throws UnsupportedEncodingException, IOException, URISyntaxException
	{
		FGBeanHistoricalQuotes locResHistQuotes = new FGBeanHistoricalQuotes();

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
