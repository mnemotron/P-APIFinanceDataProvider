package api.yahoo.finance.symbol;

import java.net.URL;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import api.yahoo.finance.QueryString;
import api.yahoo.finance.http.Get;
import api.yahoo.finance.symbol.entity.ResSymbolLookup;

/**
 * Yahoo Finance Symbol Lookup
 * @author  mnemotron
 * @version 1.0.0
 * @since 2018-01-01
 */
public class SymbolLookup
{

	private static final String PROTOCOL_HTTP = "http";
	private static final String PROTOCOL_HTTPS = "https";
	private static final String HOST_SYMBOL_LOOKUP = "autoc.finance.yahoo.com";
	private static final String PATH_SYMBOL_LOOKUP = "/autoc";
	private static final String QUERY_STRING = "query";
	private static final String QUERY_REGION = "region";
	private static final String QUERY_LANGUAGE = "lang";

	private Get httpGet;
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
	private String protocol;

	/**
	 * Default Constructor
	 */
	private SymbolLookup()
	{
		this.query = new String();
		this.region = new String();
		this.language = new String();
		this.protocol = PROTOCOL_HTTPS;
		this.httpGet = new Get();
	}
	
	/**
	 * Constructor
	 * @param proxyHostname The proxy hostname
	 * @param proxyPort The proxy port
	 */
	private SymbolLookup(String proxyHostname, int proxyPort)
	{
		this.query = new String();
		this.region = new String();
		this.language = new String();
		this.protocol = PROTOCOL_HTTPS;
		
		this.httpGet = new Get(proxyHostname, proxyPort);		
	}

	/**
	 * Factory
	 * @return Symbol lookup instance
	 */
	public static SymbolLookup FactoryGetInstance()
	{
		SymbolLookup locSymbolLookup = new SymbolLookup();

		return locSymbolLookup;
	}

	/**
	 * Factory
	 * @param proxyHostname The proxy hostname
	 * @param proxyPort The proxy port
	 * @return Symbol lookup instance
	 */
	public static SymbolLookup FactoryGetInstance(String proxyHostname, int proxyPort)
	{
		SymbolLookup locSymbolLookup = new SymbolLookup(proxyHostname, proxyPort);

		return locSymbolLookup;
	}

	/**
	 * Builds the symbol lookup URL
	 * @return URL
	 * @throws Exception
	 */
	private URL buildURL() throws Exception
	{
		String locQuery = this.buildQueryString();
		String locPath = new String();

		locPath = SymbolLookup.PATH_SYMBOL_LOOKUP + "?" + locQuery;

		URL locURL = new URL(this.protocol, SymbolLookup.HOST_SYMBOL_LOOKUP, locPath);

		return locURL;
	}

	/**
	 * Builds the symbol lookup query string
	 * @return Query string
	 * @throws Exception
	 */
	private String buildQueryString() throws Exception
	{
		QueryString locQString = new QueryString();

		locQString.add(SymbolLookup.QUERY_STRING, this.query);
		locQString.add(SymbolLookup.QUERY_REGION, this.region);
		locQString.add(SymbolLookup.QUERY_LANGUAGE, this.language);

		return locQString.getQuery();
	}

	/**
	 * Submits the symbol lookup request and returns the JSON result.
	 * @return Yahoo Finance JSON response
	 * @throws Exception
	 */
	private String getResponse() throws Exception
	{
		String locResponse = null;
		
		this.httpGet.setUrl(this.buildURL());

		locResponse = this.httpGet.getResponse();

		return locResponse;
	}

	/**
	 * Parses and binds the symbol lookup JSON response by JSON-B.
	 * @param response Yahoo Finance JSON response
	 * @see getResponse()
	 * @return Symbol lookup response object (equal to Yahoo Finance JSON result)
	 */
	private ResSymbolLookup parseResponse(String response)
	{
		Jsonb jsonb = JsonbBuilder.create();

		ResSymbolLookup locResSymbolLookup = jsonb.fromJson(response, ResSymbolLookup.class);

		return locResSymbolLookup;
	}

	/**
	 * Get result
	 * @return Symbol lookup response object
	 * @throws Exception
	 */
	public ResSymbolLookup getResult() throws Exception
	{
		ResSymbolLookup locResSymbolLookup = new ResSymbolLookup();

		// get response
		String locResponse = this.getResponse();

		// parse response
		locResSymbolLookup = this.parseResponse(locResponse);

		return locResSymbolLookup;
	}

	/**
	 * Get search string
	 * @return Search string
	 */
	public String getQuery()
	{
		return query;
	}

	/**
	 * Set search string
	 * @param query Search string
	 */
	public void setQuery(String query)
	{
		this.query = query;
	}

	/**
	 * Get region
	 * @return Region
	 */
	public String getRegion()
	{
		return region;
	}

	/**
	 * Set region
	 * For example: EU
	 * @param region Region
	 */
	public void setRegion(String region)
	{
		this.region = region;
	}

	/**
	 * Get language
	 * @return Language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * Set language
	 * For example: de-DE, en-GB
	 * @param language Language
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	public Get getHttpGet()
	{
		return httpGet;
	}

	public void setHttpGet(Get httpGet)
	{
		this.httpGet = httpGet;
	}

	public String getProtocol()
	{
		return protocol;
	}

	/**
	 * Set protocol
	 * For example: HTTP, HTTPS
	 * @param protocol
	 */
	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}
}
