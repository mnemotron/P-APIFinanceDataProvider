package api.finance.yahoo.histquote.entity;

public class Post
{
	String timezone;
	String end;
	String start;
	String gmtoffset;
	
	public String getTimezone()
	{
		return timezone;
	}
	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}
	public String getEnd()
	{
		return end;
	}
	public void setEnd(String end)
	{
		this.end = end;
	}
	public String getStart()
	{
		return start;
	}
	public void setStart(String start)
	{
		this.start = start;
	}
	public String getGmtoffset()
	{
		return gmtoffset;
	}
	public void setGmtoffset(String gmtoffset)
	{
		this.gmtoffset = gmtoffset;
	}
}
