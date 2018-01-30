package api.finance.google.symbol;

import java.io.File;
import java.net.URI;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.core5.net.URIBuilder;

import api.core.cache.CacheCookieStore;
import api.core.cache.CacheManager;
import api.core.http.HttpClient;
import api.core.http.HttpGet;
import api.core.http.Scheme;
import api.finance.google.symbol.entity.FGResSymbolLookup;

import java.util.List;

/**
 * Google Finance Symbol Lookup
 * 
 * For example: 
 * 
 * 1. 	Request to get the P3P Cookie: URL <https://finance.google.com/finance>
 * 		Response: P3P Cookie
 * 
 * 2. 	Request: URL <https://finance.google.com/finance/match?matchtype=matchall&q=google>
 * 		Response: JSON (equal to the symbol lookup entities)
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-26
 */
public class FGSymbolLookup
{
	private static final String HOST_SYMBOL_LOOKUP = "finance.google.com";
	private static final String PATH_SYMBOL_LOOKUP_FINANCE = "/finance";
	private static final String PATH_SYMBOL_LOOKUP_MATCH = "/match";
	private static final String QUERY_MATCHTYPE = "matchtype";
	private static final String QUERY_STRING = "q";
	private static final String VALUE_MATCHTYPE = "matchall";
	
	private static final String CACHE_ID_COOKIE = "cacheCookie";
	private static final String CACHE_KEY_COOKIE = "P3P";

	private HttpGet httpGet;
	private String query;
	private CacheManager cacheManager;

	/**
	 * HTTP Protocol
	 */
	private Scheme protocol;

	/**
	 * Default Constructor
	 */
	private FGSymbolLookup()
	{
		this.query = new String();
		this.protocol = Scheme.HTTPS;
		this.httpGet = new HttpGet();
		
		this.cacheManager = new CacheManager(FGSymbolLookup.CACHE_ID_COOKIE);
	}

	/**
	 * Constructor
	 * 
	 * @param proxyHostname The proxy hostname
	 * @param proxyPort The proxy port
	 */
	private FGSymbolLookup(String proxyHostname, int proxyPort)
	{
		this.query = new String();
		this.protocol = Scheme.HTTPS;

		this.httpGet = new HttpGet(proxyHostname, proxyPort);
		
		this.cacheManager = new CacheManager(FGSymbolLookup.CACHE_ID_COOKIE);
	}

	/**
	 * Factory
	 * 
	 * @return Symbol lookup instance
	 */
	public static FGSymbolLookup FactoryGetInstance()
	{
		FGSymbolLookup locSymbolLookup = new FGSymbolLookup();

		return locSymbolLookup;
	}

	/**
	 * Factory
	 * 
	 * @param proxyHostname The proxy hostname
	 * @param proxyPort The proxy port
	 * @return Symbol lookup instance
	 */
	public static FGSymbolLookup FactoryGetInstance(String proxyHostname, int proxyPort)
	{
		FGSymbolLookup locSymbolLookup = new FGSymbolLookup(proxyHostname, proxyPort);

		return locSymbolLookup;
	}
	
	/**
	 * Builds the symbol lookup URL
	 * 
	 * @return URL
	 * @throws Exception
	 */
	private URI buildURI() throws Exception
	{
		URIBuilder locURIBuilder = new URIBuilder();
		locURIBuilder.setScheme(this.protocol.getScheme());
		locURIBuilder.setHost(FGSymbolLookup.HOST_SYMBOL_LOOKUP);
		locURIBuilder.setPath(FGSymbolLookup.PATH_SYMBOL_LOOKUP_FINANCE + FGSymbolLookup.PATH_SYMBOL_LOOKUP_MATCH);
		locURIBuilder.addParameter(FGSymbolLookup.QUERY_MATCHTYPE, FGSymbolLookup.VALUE_MATCHTYPE);
		locURIBuilder.addParameter(FGSymbolLookup.QUERY_STRING, this.query);

		return locURIBuilder.build();
	}
	
	private URI buildURIP3PCookie() throws Exception
	{
		URIBuilder locURIBuilder = new URIBuilder();
		locURIBuilder.setScheme(this.protocol.getScheme());
		locURIBuilder.setHost(FGSymbolLookup.HOST_SYMBOL_LOOKUP);
		locURIBuilder.setPath(FGSymbolLookup.PATH_SYMBOL_LOOKUP_FINANCE);

		return locURIBuilder.build();
	}
	
	/**
	 * Submits the symbol lookup request and returns the JSON result.
	 * 
	 * @return Google Finance JSON response
	 * @throws Exception
	 */
	private String getResponse() throws Exception
	{	
		HttpClient locHttpClient = new HttpClient();

		if (!this.cacheManager.isCacheValid(FGSymbolLookup.CACHE_KEY_COOKIE))
		{
			//P3P cookie request
			locHttpClient.setUri(this.buildURIP3PCookie());
			locHttpClient.sendGet();
		
			CookieStore locCookieStore = locHttpClient.getCookieStore();
			
			this.cacheManager.addToCache(FGSymbolLookup.CACHE_KEY_COOKIE, new CacheCookieStore());
		}
		else
		{
			//P3P cookie from cache
			List<Cookie> locCookieList = (List<Cookie>) this.cacheManager.getFromCache(FGSymbolLookup.CACHE_KEY_COOKIE);
			
		}
		
		// symbol lookup request with cookies
		locHttpClient.setUri(this.buildURI());
		locHttpClient.sendGet();
		
		return locHttpClient.getResponse();
	}
	
	/**
	 * Parses and binds the symbol lookup JSON response by JSON-B.
	 * 
	 * @param response Google Finance JSON response
	 * @return Symbol lookup response object (equal to Google Finance JSON result)
	 */
	private FGResSymbolLookup parseResponse(String response)
	{
		Jsonb jsonb = JsonbBuilder.create();

		FGResSymbolLookup locFGResSymbolLookup = jsonb.fromJson(response, FGResSymbolLookup.class);

		return locFGResSymbolLookup;
	}
	
	/**
	 * Get result
	 * 
	 * @return Symbol lookup response object
	 * @throws Exception
	 */
	public FGResSymbolLookup getResult() throws Exception
	{
		FGResSymbolLookup locFGResSymbolLookup;

		// get response
		String locResponse = this.getResponse();

		// parse response
		locFGResSymbolLookup = this.parseResponse(locResponse);

		return locFGResSymbolLookup;
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
}
