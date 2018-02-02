package api.core.cache;

import java.io.Serializable;
import java.util.ArrayList;

public class CacheCookieStore implements Serializable
{
	private static final long serialVersionUID = -7427142344466244387L;

	private ArrayList<CacheCookie> cookieList;

	public CacheCookieStore()
	{
		this.cookieList = new ArrayList<CacheCookie>();
	}

	public ArrayList<CacheCookie> getCookieList()
	{
		return cookieList;
	}

	public void setCookieList(ArrayList<CacheCookie> cookieList)
	{
		this.cookieList = cookieList;
	}
}
