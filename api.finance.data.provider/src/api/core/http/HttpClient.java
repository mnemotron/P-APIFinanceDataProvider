package api.core.http;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.protocol.HttpClientContext;

import java.io.BufferedReader;
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

	public HttpClient()
	{
		this.uri = null;
		this.response = new String();
		this.cookieStore = new BasicCookieStore();
	}

	public HttpClient(URI uri)
	{
		this.uri = uri;
		this.response = new String();
		this.cookieStore = new BasicCookieStore();
	}

	public void sendGet() throws Exception
	{
		int intValueOfChar;
		HttpClientContext locContext = HttpClientContext.create();

		CloseableHttpClient locClient = HttpClients.createDefault();
		HttpGet locRequest = new HttpGet(this.uri);

		// add cookies
		// CookieStore locCookieStore = new BasicCookieStore();
		// BasicClientCookie locCookie = new BasicClientCookie();
		// locCookieStore.addCookie(locCookie);

		locContext.setAttribute(HttpClientContext.COOKIE_STORE, this.cookieStore);

		// execute GET
		CloseableHttpResponse locResponse = locClient.execute(locRequest, locContext);

		// return response
		BufferedReader locBR = new BufferedReader(new InputStreamReader(locResponse.getEntity().getContent()));

		while ((intValueOfChar = locBR.read()) != -1)
		{
			this.response += (char) intValueOfChar;
		}

		locClient.close();
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

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}
}
