package api.yahoo.finance.symbol;

import java.net.URL;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import api.yahoo.finance.QueryString;
import api.yahoo.finance.http.Get;
import api.yahoo.finance.symbol.entity.ResSymbolLookup;

/**
 * Yahoo Finance Symbol Lookup
 *
 * @author  mnemotron
 * @version 1.0.0
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
     *     
     * @since 1.0.0
     */
	private String region;
	
    /**
     * Language could be i.e.: de-DE, en-GB
     *     
     * @since 1.0.0
     */
	private String language;
	
    /**
     * HTTP Protocol could be HTTP, HTTPS
     *     
     * @since 1.0.0
     */
	private String protocol;

	private SymbolLookup()
	{
		this.query = new String();
		this.region = new String();
		this.language = new String();
		this.protocol = PROTOCOL_HTTPS;
		this.httpGet = new Get();
	}

	private SymbolLookup(String proxyHostname, int proxyPort)
	{
		this.query = new String();
		this.region = new String();
		this.language = new String();
		this.protocol = PROTOCOL_HTTPS;
		
		this.httpGet = new Get(proxyHostname, proxyPort);		
	}

	public static SymbolLookup FactoryGetInstance()
	{
		SymbolLookup locSymbolLookup = new SymbolLookup();

		return locSymbolLookup;
	}

	public static SymbolLookup FactoryGetInstance(String proxyHostname, int proxyPort)
	{
		SymbolLookup locSymbolLookup = new SymbolLookup(proxyHostname, proxyPort);

		return locSymbolLookup;
	}

	private URL buildURL() throws Exception
	{
		String locQuery = this.buildQueryString();
		String locPath = new String();

		locPath = SymbolLookup.PATH_SYMBOL_LOOKUP + "?" + locQuery;

		URL locURL = new URL(this.protocol, SymbolLookup.HOST_SYMBOL_LOOKUP, locPath);

		return locURL;
	}

	private String buildQueryString() throws Exception
	{
		QueryString locQString = new QueryString();

		locQString.add(SymbolLookup.QUERY_STRING, this.query);
		locQString.add(SymbolLookup.QUERY_REGION, this.region);
		locQString.add(SymbolLookup.QUERY_LANGUAGE, this.language);

		return locQString.getQuery();
	}

	private String getResponse() throws Exception
	{
		String locResponse = null;
		
		this.httpGet.setUrl(this.buildURL());

		locResponse = this.httpGet.getResponse();

		return locResponse;
	}

	private ResSymbolLookup parseResponse(String response)
	{
		Jsonb jsonb = JsonbBuilder.create();

		ResSymbolLookup locResSymbolLookup = jsonb.fromJson(response, ResSymbolLookup.class);

		return locResSymbolLookup;
	}

	public ResSymbolLookup getResult() throws Exception
	{
		ResSymbolLookup locResSymbolLookup = new ResSymbolLookup();

		// get response
		String locResponse = this.getResponse();

		// parse response
		locResSymbolLookup = this.parseResponse(locResponse);

		return locResSymbolLookup;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public String getRegion()
	{
		return region;
	}

	public void setRegion(String region)
	{
		this.region = region;
	}

	public String getLanguage()
	{
		return language;
	}

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

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}
}
