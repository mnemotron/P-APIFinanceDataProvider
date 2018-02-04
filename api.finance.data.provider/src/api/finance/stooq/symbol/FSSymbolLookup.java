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
package api.finance.stooq.symbol;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hc.core5.net.URIBuilder;

import api.core.http.HttpClient;
import api.core.http.Scheme;
import api.core.ticker.entity.Tickers;

/**
 * Stooq Symbol Lookup
 * 
 * For example:
 * 
 * Request: i.e. URL <https://stooq.com/cmp/?q=g>
 * Response: i.e. window.cmp_r('<b>G</b>BPPLN~British Pounds / Polish Zloty 1:1~Currency~4.72471~-0.28%~5|<b>G</b>C.F~<b>G</b>old - COMEX~Commodities Futures~1337.30~-1.08%~2|<b>G</b>NB~<b>G</b>etin Noble Bank SA~Warsaw SE~1.75~-1.69%~2|<b>G</b>BK~<b>G</b>etBack SA~Warsaw SE~18.10~0.11%~2|<b>G</b>BPUSD~British Pounds / U.S. Dollar 1:1~Currency~1.41212~-1.00%~5|<b>G</b>RI~<b>G</b>ino Rossi SA~Warsaw SE~1.21~-0.82%~2|<b>G</b>TN~<b>G</b>etin Holdin<b>g</b> SA~Warsaw SE~1.46~-2.67%~2|<b>G</b>CN~<b>G</b>roclin SA~Warsaw SE~5.83~0.52%~2|<b>G</b>BPJPY~British Pounds / Japanese Yen 1:1~Currency~155.481~-0.43%~3|<b>G</b>PW~<b>G</b>iełda Papierów Wartościowych w Warszawie SA~Warsaw SE~46.30~-2.73%~2');
 * 
 * @author mnemotron
 * @version 1.3.0
 * @since 2018-02-04
 */
public class FSSymbolLookup
{
	private static final String HOST_SYMBOL_LOOKUP = "stooq.com";
	private static final String PATH_SYMBOL_LOOKUP = "/cmp";
	private static final String QUERY_STRING = "q";
	
	/**
	 * Query string
	 */
	private String query;

	/**
	 * HTTP Protocol could be HTTP, HTTPS
	 */
	private Scheme protocol;

	/**
	 * Default Constructor
	 */
	private FSSymbolLookup()
	{
		this.query = new String();
		this.protocol = Scheme.HTTPS;
	}
	
	/**
	 * Factory
	 * 
	 * @return Symbol lookup instance
	 */
	public static FSSymbolLookup FactoryGetInstance()
	{
		FSSymbolLookup locSymbolLookup = new FSSymbolLookup();

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
		locURIBuilder.setHost(FSSymbolLookup.HOST_SYMBOL_LOOKUP);
		locURIBuilder.setPath(FSSymbolLookup.PATH_SYMBOL_LOOKUP);
		locURIBuilder.addParameter(FSSymbolLookup.QUERY_STRING, this.query);

		return locURIBuilder.build();
	}
	
	/**
	 * Submits the symbol lookup request and returns the result.
	 * 
	 * @return Proprietary response
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
	 * Parses the proprietary format
	 * 
	 * @param response Proprietary format
	 * @return Symbol lookup response object
	 */
	private Tickers parseResponse(String response)
	{
		Tickers locSymbolLookup = new Tickers();

		//window.cmp_r('<b>G</b>BPPLN~British Pounds / Polish Zloty 1:1~Currency~4.72471~-0.28%~5|<b>G</b>C.F~<b>G</b>old - COMEX~Commodities Futures~1337.30~-1.08%~2|<b>G</b>NB~<b>G</b>etin Noble Bank SA~Warsaw SE~1.75~-1.69%~2|<b>G</b>BK~<b>G</b>etBack SA~Warsaw SE~18.10~0.11%~2|<b>G</b>BPUSD~British Pounds / U.S. Dollar 1:1~Currency~1.41212~-1.00%~5|<b>G</b>RI~<b>G</b>ino Rossi SA~Warsaw SE~1.21~-0.82%~2|<b>G</b>TN~<b>G</b>etin Holdin<b>g</b> SA~Warsaw SE~1.46~-2.67%~2|<b>G</b>CN~<b>G</b>roclin SA~Warsaw SE~5.83~0.52%~2|<b>G</b>BPJPY~British Pounds / Japanese Yen 1:1~Currency~155.481~-0.43%~3|<b>G</b>PW~<b>G</b>iełda Papierów Wartościowych w Warszawie SA~Warsaw SE~46.30~-2.73%~2');
		  
//{tickerID~tickerName~}|{}
		
		return locSymbolLookup;
	}
	
	/**
	 * Get result
	 * 
	 * @return Symbol lookup response object
	 * @throws Exception
	 */
	public Tickers getResult() throws Exception
	{
		Tickers locSymbolLookup = new Tickers();

		// get response
		String locResponse = this.getResponse();

		// parse response
		locSymbolLookup = this.parseResponse(locResponse);

		return locSymbolLookup;
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
	 * @param query
	 *            Search string
	 */
	public void setQuery(String query)
	{
		this.query = query;
	}
}
