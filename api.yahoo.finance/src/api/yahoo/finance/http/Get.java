package api.yahoo.finance.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * GET
 * @author  mnemotron
 * @version 1.0.0
 * @since 2018-01-01
 */
public class Get
{
	private Proxy proxy;
	private URL url;

	public Get()
	{
		this.proxy = null;
		this.url = null;
	}

	public Get(String proxyHostname, int proxyPort)
	{
		this.url = null;
		this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostname, proxyPort));
	}

	public Get(String proxyHostname, int proxyPort, URL url)
	{
		this.url = url;
		this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostname, proxyPort));
	}

	public String getResponse() throws IOException
	{

		String locResponse = new String();
		URLConnection locURLC = null;

		if (this.isProxy())
		{
			locURLC = this.url.openConnection(this.proxy);
		}
		else
		{
			locURLC = this.url.openConnection();
		}

		Scanner locScanner = new Scanner(locURLC.getInputStream());

		while (locScanner.hasNext())
		{
			locResponse = locResponse.concat(locScanner.nextLine());
		}

		locScanner.close();

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
