package api.finance.yahoo.histquote.entity;

import java.util.List;

public class Quote
{
	List<String> volume;
	List<String> low;
	List<String> close;
	List<String> high;
	List<String> open;
	
	public List<String> getVolume()
	{
		return volume;
	}
	public void setVolume(List<String> volume)
	{
		this.volume = volume;
	}
	public List<String> getLow()
	{
		return low;
	}
	public void setLow(List<String> low)
	{
		this.low = low;
	}
	public List<String> getClose()
	{
		return close;
	}
	public void setClose(List<String> close)
	{
		this.close = close;
	}
	public List<String> getHigh()
	{
		return high;
	}
	public void setHigh(List<String> high)
	{
		this.high = high;
	}
	public List<String> getOpen()
	{
		return open;
	}
	public void setOpen(List<String> open)
	{
		this.open = open;
	}
}
