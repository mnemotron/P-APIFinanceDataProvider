package api.finance.yahoo.symbol;

import java.net.URL;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.hc.core5.net.URIBuilder;

import api.core.http.HttpGet;
import api.core.http.Scheme;
import api.finance.yahoo.symbol.entity.FYResSymbolLookup;

/**
 * Yahoo Finance Symbol Lookup
 * 
 * For example: 
 * Request: URL <https://autoc.finance.yahoo.com/autoc?query=bmw&region=EU&lang=en-GB>
 * Response: JSON (equal to the symbol lookup entities)
 * 
 * Alternative (not implemented): Request: URL
 * <http://d.yimg.com/aq/autoc?query=bmw&region=DE&lang=en-US&callback=YAHOO.util.ScriptNodeDataSource.callbacks>
 * 
 * @author mnemotron
 * @version 1.0.0
 * @since 2018-01-01
 */
public class FYSymbolLookup
{
	private static final String HOST_SYMBOL_LOOKUP = "autoc.finance.yahoo.com";
	private static final String PATH_SYMBOL_LOOKUP = "/autoc";
	private static final String QUERY_STRING = "query";
	private static final String QUERY_REGION = "region";
	private static final String QUERY_LANGUAGE = "lang";

	private HttpGet httpGet;
	private String query;

	/**
	 * Region could be i.e.: EU
	 */
	private String region;

	/**
	 * Language could be i.e.: de-DE, en-GB
	 */
	private String language;

	/**
	 * HTTP Protocol could be HTTP, HTTPS
	 */
	private Scheme protocol;

	/**
	 * Default Constructor
	 */
	private FYSymbolLookup()
	{
		this.query = new String();
		this.region = new String();
		this.language = new String();
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
	private FYSymbolLookup(String proxyHostname, int proxyPort)
	{
		this.query = new String();
		this.region = new String();
		this.language = new String();
		this.protocol = Scheme.HTTPS;

		this.httpGet = new HttpGet(proxyHostname, proxyPort);
	}

	/**
	 * Factory
	 * 
	 * @return Symbol lookup instance
	 */
	public static FYSymbolLookup FactoryGetInstance()
	{
		FYSymbolLookup locSymbolLookup = new FYSymbolLookup();

		return locSymbolLookup;
	}

	/**
	 * Factory
	 * 
	 * @param proxyHostname
	 *            The proxy hostname
	 * @param proxyPort
	 *            The proxy port
	 * @return Symbol lookup instance
	 */
	public static FYSymbolLookup FactoryGetInstance(String proxyHostname, int proxyPort)
	{
		FYSymbolLookup locSymbolLookup = new FYSymbolLookup(proxyHostname, proxyPort);

		return locSymbolLookup;
	}

	/**
	 * Builds the symbol lookup URL
	 * 
	 * @return URL
	 * @throws Exception
	 */
	private URL buildURL() throws Exception
	{
		URIBuilder locURIBuilder = new URIBuilder();
		locURIBuilder.setScheme(this.protocol.getScheme());
		locURIBuilder.setHost(FYSymbolLookup.HOST_SYMBOL_LOOKUP);
		locURIBuilder.setPath(FYSymbolLookup.PATH_SYMBOL_LOOKUP);
		locURIBuilder.addParameter(FYSymbolLookup.QUERY_STRING, this.query);
		locURIBuilder.addParameter(FYSymbolLookup.QUERY_REGION, this.region);
		locURIBuilder.addParameter(FYSymbolLookup.QUERY_LANGUAGE, this.language);

		return locURIBuilder.build().toURL();
	}

	/**
	 * Submits the symbol lookup request and returns the JSON result.
	 * 
	 * @return Yahoo Finance JSON response
	 * @throws Exception
	 */
	private String getResponse() throws Exception
	{
		String locResponse = null;

		this.httpGet.setUrl(this.buildURL());

		this.httpGet.sendGet();
		locResponse = this.httpGet.getResponse();

		return locResponse;
	}

	/**
	 * Parses and binds the symbol lookup JSON response by JSON-B.
	 * 
	 * @param response Yahoo Finance JSON response
	 * @return Symbol lookup response object (equal to Yahoo Finance JSON result)
	 */
	private FYResSymbolLookup parseResponse(String response)
	{
		Jsonb jsonb = JsonbBuilder.create();

		FYResSymbolLookup locResSymbolLookup = jsonb.fromJson(response, FYResSymbolLookup.class);

		return locResSymbolLookup;
	}

	/**
	 * Get result
	 * 
	 * @return Symbol lookup response object
	 * @throws Exception
	 */
	public FYResSymbolLookup getResult() throws Exception
	{
		FYResSymbolLookup locResSymbolLookup;

		// get response
		String locResponse = this.getResponse();

		// parse response
		locResSymbolLookup = this.parseResponse(locResponse);

		return locResSymbolLookup;
	}

	/**
	 * Get search string
	 * 
	 * @return Search string
	 */
	public String getQuery()
	{
		return query;
	}

	/**
	 * Set search string
	 * 
	 * @param query Search string
	 */
	public void setQuery(String query)
	{
		this.query = query;
	}

	/**
	 * Get region
	 * 
	 * @return Region
	 */
	public String getRegion()
	{
		return region;
	}

	/**
	 * Set region For example: EU
	 * 
	 * @param region Region
	 */
	public void setRegion(String region)
	{
		this.region = region;
	}

	/**
	 * Get language
	 * 
	 * @return Language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Set language For example: de-DE, en-GB
	 * 
	 * @param language Language
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	public HttpGet getHttpGet()
	{
		return httpGet;
	}

	public void setHttpGet(HttpGet httpGet)
	{
		this.httpGet = httpGet;
	}

	public Scheme getProtocol()
	{
		return protocol;
	}

	/**
	 * Set protocol For example: HTTP, HTTPS
	 * 
	 * @param protocol
	 */
	public void setProtocol(Scheme protocol)
	{
		this.protocol = protocol;
	}
}
