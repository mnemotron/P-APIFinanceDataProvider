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
package api.finance.yahoo.symbol;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.hc.core5.net.URIBuilder;

import api.core.http.HttpClient;
import api.core.http.Scheme;
import api.finance.yahoo.symbol.entity.FYBeanSymbolLookup;

/**
 * Yahoo Finance Symbol Lookup
 * 
 * For example: 
 * Request: URL <https://autoc.finance.yahoo.com/autoc?query=bmw&region=EU&lang=en-GB>
 * Response: JSON (equal to the symbol lookup entities)
 * 
 * Alternative (not implemented): 
 * Request: URL <http://d.yimg.com/aq/autoc?query=bmw&region=DE&lang=en-US&callback=YAHOO.util.ScriptNodeDataSource.callbacks>
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

	/**
	 * Query string
	 */
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
	 * @return URI
	 * @throws URISyntaxException
	 * @throws Exception
	 */
	private URI buildURI() throws URISyntaxException
	{
		URIBuilder locURIBuilder = new URIBuilder();
		locURIBuilder.setScheme(this.protocol.getScheme());
		locURIBuilder.setHost(FYSymbolLookup.HOST_SYMBOL_LOOKUP);
		locURIBuilder.setPath(FYSymbolLookup.PATH_SYMBOL_LOOKUP);
		locURIBuilder.addParameter(FYSymbolLookup.QUERY_STRING, this.query);
		locURIBuilder.addParameter(FYSymbolLookup.QUERY_REGION, this.region);
		locURIBuilder.addParameter(FYSymbolLookup.QUERY_LANGUAGE, this.language);

		return locURIBuilder.build();
	}

	/**
	 * Submits the symbol lookup request and returns the JSON result.
	 * 
	 * @return Yahoo Finance JSON response
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private String getResponse() throws URISyntaxException, IOException
	{
		String locResponse = new String();

		HttpClient locHttpClient = HttpClient.FactoryGetInstance(this.buildURI());

		locHttpClient.sendGet();

		locResponse = locHttpClient.getResponse();

		return locResponse;
	}

	/**
	 * Parses and binds the symbol lookup JSON response by JSON-B.
	 * 
	 * @param response
	 *            Yahoo Finance JSON response
	 * @return Symbol lookup response object (equal to Yahoo Finance JSON
	 *         result)
	 */
	private FYBeanSymbolLookup parseResponse(String response)
	{
		FYBeanSymbolLookup locResSymbolLookup = new FYBeanSymbolLookup();

		try
		{
			Jsonb jsonb = JsonbBuilder.create();

			locResSymbolLookup = jsonb.fromJson(response, FYBeanSymbolLookup.class);
		}
		catch (Exception e)
		{
			//return empty object, if the parsing fails
		}

		return locResSymbolLookup;
	}

	/**
	 * Get result
	 * 
	 * @return Symbol lookup response object
	 * @throws Exception
	 */
	public FYBeanSymbolLookup getResult() throws Exception
	{
		FYBeanSymbolLookup locSymbolLookup = new FYBeanSymbolLookup();

		// get response
		String locResponse = this.getResponse();

		// parse response
		locSymbolLookup = this.parseResponse(locResponse);

		return locSymbolLookup;
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
	 * @param query
	 *            Search string
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
	 * @param region
	 *            Region
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
	 * @param language
	 *            Language
	 */
	public void setLanguage(String language)
	{
		this.language = language;
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
