package api.yahoo.finance.symbol;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import api.yahoo.finance.QueryString;
import api.yahoo.finance.symbol.entity.ResSymbolLookup;

public class SymbolLookup
{

	private static final String PROTOCOL_HTTP = "http";
	private static final String PROTOCOL_HTTPS = "https";
	private static final String HOST_SYMBOL_LOOKUP = "autoc.finance.yahoo.com";
	private static final String PATH_SYMBOL_LOOKUP = "/autoc";
	private static final String QUERY_STRING = "query";
	private static final String QUERY_REGION = "region";
	private static final String QUERY_LANGUAGE = "lang";

	private Proxy proxy;
	private String query;
	private String region;
	private String language;

	private SymbolLookup()
	{

		this.proxy = null;
		this.query = new String();
		this.region = new String();
		this.language = new String();
	}

	private SymbolLookup(String proxyHostname, int proxyPort)
	{

		this.query = new String();
		this.region = new String();
		this.language = new String();

		this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostname, proxyPort));
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

		URL locURL = new URL(SymbolLookup.PROTOCOL_HTTPS, SymbolLookup.HOST_SYMBOL_LOOKUP, locPath);

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
		String locResponse = new String();
		URLConnection locURLC = null;

		URL locURL = this.buildURL();

		if (this.isProxy())
		{
			locURLC = locURL.openConnection(this.proxy);
		}
		else
		{
			locURLC = locURL.openConnection();
		}

		Scanner locScanner = new Scanner(locURLC.getInputStream());

		while (locScanner.hasNext())
		{
			locResponse = locResponse.concat(locScanner.nextLine());
		}

		locScanner.close();

		return locResponse;
	}

	private boolean isProxy()
	{
		return (this.proxy == null) ? false : true;
	}

	private ResSymbolLookup parseResponse(String response)
	{
		Jsonb jsonb = JsonbBuilder.create();

		ResSymbolLookup locResSymbolLookup = jsonb.fromJson(response, ResSymbolLookup.class);
		
		return locResSymbolLookup;
	}

	public ResSymbolLookup getResult() throws Exception
	{
		ResSymbolLookup  locResSymbolLookup = new ResSymbolLookup();
		
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
}
