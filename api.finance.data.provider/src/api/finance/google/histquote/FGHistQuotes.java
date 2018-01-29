package api.finance.google.histquote;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.hc.core5.net.URIBuilder;

import com.opencsv.bean.CsvToBeanBuilder;

import api.core.http.HttpGet;
import api.core.http.Scheme;
import api.finance.google.histquote.entity.FGHistoricalQuote;
import api.finance.google.histquote.entity.FGHistoricalQuotes;

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
public class FGHistQuotes
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
	private HttpGet httpGet;
	private String tickerID;

	/**
	 * Default Constructor
	 */
	private FGHistQuotes()
	{
		this.from = null;
		this.to = null;
		this.protocol = Scheme.HTTPS;
		this.httpGet = new HttpGet();
	}

	/**
	 * Constructor
	 * 
	 * @param proxyHostname
	 *            The proxy hostname
	 * @param proxyPort
	 *            The proxy port
	 */
	private FGHistQuotes(String proxyHostname, int proxyPort)
	{
		this.from = null;
		this.to = null;
		this.protocol = Scheme.HTTPS;
		this.httpGet = new HttpGet(proxyHostname, proxyPort);
	}

	/**
	 * Factory
	 * 
	 * @return Historical quotes instance
	 */
	public static FGHistQuotes FactoryGetInstance()
	{
		FGHistQuotes locHistQuotes = new FGHistQuotes();

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
	public static FGHistQuotes FactoryGetInstance(String proxyHostname, int proxyPort)
	{
		FGHistQuotes locHistQuotes = new FGHistQuotes(proxyHostname, proxyPort);

		return locHistQuotes;
	}

	/**
	 * Builds the URL
	 * 
	 * @return URL
	 * @throws Exception
	 */
	private URL buildURL() throws Exception
	{
		URIBuilder locURIBuilder = new URIBuilder();
		locURIBuilder.setScheme(this.protocol.getScheme());
		locURIBuilder.setHost(FGHistQuotes.HOST_SYMBOL_LOOKUP);
		locURIBuilder.setPath(FGHistQuotes.PATH_SYMBOL_LOOKUP);
		locURIBuilder.addParameter(FGHistQuotes.QUERY_TICKERID, this.tickerID);

		// TODO Google Query Date Format
		DateFormat locDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String locStartDate = locDateFormat.format(this.from.getTime());
		String locEndDate = locDateFormat.format(this.to.getTime());

		locURIBuilder.addParameter(FGHistQuotes.QUERY_START_DATE, locStartDate);
		locURIBuilder.addParameter(FGHistQuotes.QUERY_END_DATE, locEndDate);
		locURIBuilder.addParameter(FGHistQuotes.QUERY_OUTPUT, VALUE_OUTPUT_CSV);

		return locURIBuilder.build().toURL();
	}

	/**
	 * Submits the historical quote request and returns the CSV result.
	 * 
	 * @return CSV
	 * @throws Exception
	 */
	private String getResponse() throws Exception
	{
		String locResponse;

		this.httpGet.setUrl(this.buildURL());

		this.httpGet.sendGet();
		locResponse = this.httpGet.getResponse();

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
	private FGHistoricalQuotes parseResponse(String response) throws UnsupportedEncodingException, IOException
	{
		FGHistoricalQuotes locResHistQuotes = new FGHistoricalQuotes();
		
	    InputStreamReader locReaderBOM = new InputStreamReader(new BOMInputStream(IOUtils.toInputStream(response, StandardCharsets.UTF_8.name())), StandardCharsets.UTF_8.name());
		
		String locResponseWithoutBOM = IOUtils.toString(locReaderBOM);
		
		locReaderBOM.close();
		
		List<FGHistoricalQuote> locHistQuoteList = 
				new CsvToBeanBuilder<FGHistoricalQuote>(new StringReader(locResponseWithoutBOM))
						.withType(FGHistoricalQuote.class)
						.build()
						.parse();
		
		locResHistQuotes.setHistQuoteList(new ArrayList<FGHistoricalQuote>(locHistQuoteList));

		return locResHistQuotes;
	}

	/**
	 * Get result
	 * 
	 * @return Historical quote response object
	 * @throws Exception
	 */
	public FGHistoricalQuotes getResult() throws Exception
	{
		FGHistoricalQuotes locResHistQuotes;

		// get response
		String locResponse = this.getResponse();

		// parse response
		locResHistQuotes = this.parseResponse(locResponse);

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

	public HttpGet getHttpGet()
	{
		return httpGet;
	}

	public void setHttpGet(HttpGet httpGet)
	{
		this.httpGet = httpGet;
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
