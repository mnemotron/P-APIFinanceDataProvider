package api.yahoo.finance.symbol;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import api.yahoo.finance.QueryString;

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

		URL locURL = new URL(SymbolLookup.PROTOCOL_HTTP, SymbolLookup.HOST_SYMBOL_LOOKUP, locPath);

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

	private void parseResponse(String response)
	{

		String locResponse = new String(
				"{\"ResultSet\":{\"Query\":\"alphabet\",\"Result\":[{\"symbol\":\"GOOGL-USD.SW\",\"name\":\"Alphabet\",\"exch\":\"EBS\",\"type\":\"S\",\"exchDisp\":\"Swiss\",\"typeDisp\":\"Equity\"},{\"symbol\":\"GOOG\",\"name\":\"Alphabet Inc.\",\"exch\":\"NGM\",\"type\":\"S\",\"exchDisp\":\"NASDAQ\",\"typeDisp\":\"Equity\"},{\"symbol\":\"GOOGL\",\"name\":\"Alphabet Inc.\",\"exch\":\"NAS\",\"type\":\"S\",\"exchDisp\":\"NASDAQ\",\"typeDisp\":\"Equity\"},{\"symbol\":\"GOOG.SN\",\"name\":\"ALPHABET INC\",\"exch\":\"SGO\",\"type\":\"S\",\"exchDisp\":\"Santiago\",\"typeDisp\":\"Equity\"},{\"symbol\":\"GOOGL.SW\",\"name\":\"Alphabet Inc.\",\"exch\":\"EBS\",\"type\":\"S\",\"exchDisp\":\"Swiss\",\"typeDisp\":\"Equity\"},{\"symbol\":\"ABEC.MU\",\"name\":\"ALPHABET INC.CL C DL-,001\",\"exch\":\"MUN\",\"type\":\"S\",\"exchDisp\":\"Munich\",\"typeDisp\":\"Equity\"},{\"symbol\":\"ABEC.SG\",\"name\":\"Alphabet Inc. Reg. Shs Cap.Stk \",\"exch\":\"STU\",\"type\":\"S\",\"exchDisp\":\"Stuttgart\",\"typeDisp\":\"Equity\"},{\"symbol\":\"GOOC.VI\",\"name\":\"Alphabet Inc.\",\"exch\":\"VIE\",\"type\":\"S\",\"exchDisp\":\"Vienna\",\"typeDisp\":\"Equity\"},{\"symbol\":\"ABEA.F\",\"name\":\"Alphabet Inc.\",\"exch\":\"FRA\",\"type\":\"S\",\"exchDisp\":\"Frankfurt\",\"typeDisp\":\"Equity\"},{\"symbol\":\"ABEC.F\",\"name\":\"Alphabet Inc.\",\"exch\":\"FRA\",\"type\":\"S\",\"exchDisp\":\"Frankfurt\",\"typeDisp\":\"Equity\"}]}}");

		Jsonb jsonb = JsonbBuilder.create();

		ResSymbolLookup locResSymbolLookup = jsonb.fromJson(locResponse, ResSymbolLookup.class);

		// JsonParser locJsonParser = Json.createParser(new
		// StringReader(locResponse));
		//
		//
		// while(locJsonParser.hasNext())
		// {
		// switch (locJsonParser.next())
		// {
		// case KEY_NAME: //ResultSet
		//
		// String key = locJsonParser.getString();
		// locJsonParser.next();
		//
		// switch (key)
		// {
		// case "Query":
		//// movie.setId(parser.getInt());
		// break;
		// case ""
		// default:
		// break;
		// }
		// break;
		// default:
		// break;
		// }
		//
		// }
		////
		// JsonObject locJO = locJsonParser.getObject();
		// JsonObject locResultSet = locJO.getJsonObject("ResultSet");
		// JsonArray locResult = locResultSet.getJsonArray("Result");
		//
		// for (int i = 0, l = locResult.size(); l < i; i++) {
		// JsonObject locR = locResult.getJsonObject(i);
		// JsonString locSymbol = locR.getJsonString("symbol");
		//
		// System.out.println(locSymbol.toString());
		// }

	}

	public ArrayList<Symbol> getResult() throws Exception
	{

		ArrayList<Symbol> locSymbolList = new ArrayList<Symbol>();

		// get response
		String locResponse = new String();// this.getResponse();

		System.out.println(locResponse);

		// parse response
		this.parseResponse(locResponse);

		return locSymbolList;
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
