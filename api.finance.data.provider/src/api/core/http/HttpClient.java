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
package api.core.http;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.protocol.HttpClientContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;

/**
 * HTTP Client
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public class HttpClient
{
	private URI uri;;
	private CookieStore cookieStore;
	private String response;
	private int code;

	public static HttpClient FactoryGetInstance(URI uri)
	{
		HttpClient locHttpClient = new HttpClient(uri);

		return locHttpClient;
	}

	private HttpClient(URI uri)
	{
		this.uri = uri;
		this.cookieStore = new BasicCookieStore();
		this.response = new String();
		this.code = 0;
	}

	public void sendGet() throws IOException
	{
		HttpClientContext locContext = HttpClientContext.create();

		HttpGet locRequest = new HttpGet(this.uri);

		locContext.setAttribute(HttpClientContext.COOKIE_STORE, this.cookieStore);

		// execute GET
		CloseableHttpClient locClient = HttpClients.createDefault();
		CloseableHttpResponse locHttpRes = locClient.execute(locRequest, locContext);

		// store response
		this.storeResponse(locHttpRes);

		locClient.close();
	}

	private void storeResponse(CloseableHttpResponse httpRes) throws UnsupportedOperationException, IOException
	{
		int intValueOfChar;
		StringBuilder locResponse = new StringBuilder();
		BufferedReader locBR = new BufferedReader(new InputStreamReader(httpRes.getEntity().getContent()));

		while ((intValueOfChar = locBR.read()) != -1)
		{
			locResponse.append((char) intValueOfChar);
		}

		this.response = locResponse.toString();
		this.code = httpRes.getCode();
	}

	public URI getUri()
	{
		return uri;
	}

	public void setUri(URI uri)
	{
		this.uri = uri;
	}

	public CookieStore getCookieStore()
	{
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore)
	{
		this.cookieStore = cookieStore;
	}

	public String getResponse()
	{
		return response;
	}

	public int getCode()
	{
		return code;
	}
}
