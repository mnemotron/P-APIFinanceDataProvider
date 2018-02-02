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

public class HttpClient
{
	private URI uri;
	private String response;
	private CookieStore cookieStore;
	private CloseableHttpResponse responseHeader;

	public HttpClient()
	{
		this.uri = null;
		this.response = new String();
		this.cookieStore = new BasicCookieStore();
		this.responseHeader = null;
	}

	public HttpClient(URI uri)
	{
		this.uri = uri;
		this.response = new String();
		this.cookieStore = new BasicCookieStore();
		this.responseHeader = null;
	}

	public void sendGet() throws IOException
	{
		int intValueOfChar;
		HttpClientContext locContext = HttpClientContext.create();

		this.refreshResponse();

		CloseableHttpClient locClient = HttpClients.createDefault();
		HttpGet locRequest = new HttpGet(this.uri);

		locContext.setAttribute(HttpClientContext.COOKIE_STORE, this.cookieStore);

		// execute GET
		this.responseHeader = locClient.execute(locRequest, locContext);

		// return response
		BufferedReader locBR = new BufferedReader(new InputStreamReader(this.responseHeader.getEntity().getContent()));

		while ((intValueOfChar = locBR.read()) != -1)
		{
			this.response += (char) intValueOfChar;
		}

		locClient.close();
	}

	public void refreshResponse()
	{
		this.response = new String();
		this.responseHeader = null;

	}

	public URI getUri()
	{
		return uri;
	}

	public void setUri(URI uri)
	{
		this.uri = uri;
	}

	public String getResponse()
	{
		return response;
	}

	public void setResponse(String response)
	{
		this.response = response;
	}

	public CookieStore getCookieStore()
	{
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore)
	{
		this.cookieStore = cookieStore;
	}

	public CloseableHttpResponse getResponseHeader()
	{
		return responseHeader;
	}
}
