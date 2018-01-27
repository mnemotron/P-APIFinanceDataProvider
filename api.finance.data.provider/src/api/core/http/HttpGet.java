package api.core.http;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

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
	
	private String response;
	private Map<String, List<String>> headerFields;

	public HttpGet()
	{
		this.proxy = null;
		this.url = null;
		this.response = new String();
	}

	public HttpGet(String proxyHostname, int proxyPort)
	{
		this.url = null;
		this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostname, proxyPort));
		this.response = new String();
	}

	public HttpGet(String proxyHostname, int proxyPort, URL url)
	{
		this.url = url;
		this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostname, proxyPort));
		this.response = new String();
	}
	
	public void sendGet() throws IOException
	{
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
		
		this.headerFields = locURLC.getHeaderFields();
		
	    Reader responseReader = new InputStreamReader(locURLC.getInputStream());	

	    while((intValueOfChar = responseReader.read()) != -1)
	    {
	    	this.response += (char) intValueOfChar;
	    }
	 
	    responseReader.close();
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

	public String getResponse()
	{
		return response;
	}

	public void setResponse(String response)
	{
		this.response = response;
	}

	public Map<String, List<String>> getHeaderFields()
	{
		return headerFields;
	}

	public void setHeaderFields(Map<String, List<String>> headerFields)
	{
		this.headerFields = headerFields;
	}
}
