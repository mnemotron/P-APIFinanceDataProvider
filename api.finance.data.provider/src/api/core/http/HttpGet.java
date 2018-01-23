package api.core.http;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 * GET
 * @author  mnemotron
 * @version 1.0.0
 * @since 2018-01-01
 */
public class HttpGet
{
	private Proxy proxy;
	private URL url;

	public HttpGet()
	{
		this.proxy = null;
		this.url = null;
	}

	public HttpGet(String proxyHostname, int proxyPort)
	{
		this.url = null;
		this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostname, proxyPort));
	}

	public HttpGet(String proxyHostname, int proxyPort, URL url)
	{
		this.url = url;
		this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostname, proxyPort));
	}

	public String getResponse() throws IOException
	{

		String locResponse = new String();
	    int intValueOfChar;
		URLConnection locURLC = null;

		if (this.isProxy())
		{
			locURLC = this.url.openConnection(this.proxy);
		}
		else
		{
			locURLC = this.url.openConnection();
		}
		
	    Reader responseReader = new InputStreamReader(locURLC.getInputStream());	

	    while((intValueOfChar = responseReader.read()) != -1)
	    {
	    	locResponse += (char) intValueOfChar;
	    }
	 
	    responseReader.close();

		return locResponse;
	}

	public Proxy getProxy()
	{
		return proxy;
	}

	public void setProxy(Proxy proxy)
	{
		this.proxy = proxy;
	}

	public URL getUrl()
	{
		return url;
	}

	public void setUrl(URL url)
	{
		this.url = url;
	}

	private boolean isProxy()
	{
		return (this.proxy == null) ? false : true;
	}
}
