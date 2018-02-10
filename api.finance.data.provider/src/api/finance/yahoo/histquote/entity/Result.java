package api.finance.yahoo.histquote.entity;

import java.util.List;

public class Result
{
	Meta meta;
	List<String> timestamp;
	Indicators indicators;
	
	public Meta getMeta()
	{
		return meta;
	}
	public void setMeta(Meta meta)
	{
		this.meta = meta;
	}
	public List<String> getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(List<String> timestamp)
	{
		this.timestamp = timestamp;
	}
	public Indicators getIndicators()
	{
		return indicators;
	}
	public void setIndicators(Indicators indicators)
	{
		this.indicators = indicators;
	}
}
