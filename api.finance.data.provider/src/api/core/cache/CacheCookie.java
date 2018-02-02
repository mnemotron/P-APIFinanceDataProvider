package api.core.cache;

import java.io.Serializable;
import java.util.Date;

public class CacheCookie implements Serializable
{
	private static final long serialVersionUID = -6274095368332895488L;

	private Date creationDate;
	private String domain;
	private String name;
	private String path;
	private String value;
	private Date expiryDate;

	public CacheCookie()
	{
		this.creationDate = null;
		this.domain = new String();
		this.name = new String();
		this.path = new String();
		this.value = new String();
		this.expiryDate = null;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	public String getDomain()
	{
		return domain;
	}

	public void setDomain(String domain)
	{
		this.domain = domain;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Date getExpiryDate()
	{
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate)
	{
		this.expiryDate = expiryDate;
	}
}
