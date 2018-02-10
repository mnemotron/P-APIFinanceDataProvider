package api.finance.yahoo.histquote.entity;

import java.util.List;

public class Meta
{
	String currency;
	String symbol;
	String exchangeName;
	String instrumentType;
	String firstTradeDate;
	String gmtoffset;
	String timezone;
	String exchangeTimezoneName;
	String chartPreviousClose;
	CurrentTradingPeriod currentTradingPeriod;
	String dataGranularity;
	List<String> validRanges;
	
	public String getCurrency()
	{
		return currency;
	}
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	public String getSymbol()
	{
		return symbol;
	}
	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}
	public String getExchangeName()
	{
		return exchangeName;
	}
	public void setExchangeName(String exchangeName)
	{
		this.exchangeName = exchangeName;
	}
	public String getInstrumentType()
	{
		return instrumentType;
	}
	public void setInstrumentType(String instrumentType)
	{
		this.instrumentType = instrumentType;
	}
	public String getFirstTradeDate()
	{
		return firstTradeDate;
	}
	public void setFirstTradeDate(String firstTradeDate)
	{
		this.firstTradeDate = firstTradeDate;
	}
	public String getGmtoffset()
	{
		return gmtoffset;
	}
	public void setGmtoffset(String gmtoffset)
	{
		this.gmtoffset = gmtoffset;
	}
	public String getTimezone()
	{
		return timezone;
	}
	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}
	public String getExchangeTimezoneName()
	{
		return exchangeTimezoneName;
	}
	public void setExchangeTimezoneName(String exchangeTimezoneName)
	{
		this.exchangeTimezoneName = exchangeTimezoneName;
	}
	public String getChartPreviousClose()
	{
		return chartPreviousClose;
	}
	public void setChartPreviousClose(String chartPreviousClose)
	{
		this.chartPreviousClose = chartPreviousClose;
	}
	public CurrentTradingPeriod getCurrentTradingPeriod()
	{
		return currentTradingPeriod;
	}
	public void setCurrentTradingPeriod(CurrentTradingPeriod currentTradingPeriod)
	{
		this.currentTradingPeriod = currentTradingPeriod;
	}
	public String getDataGranularity()
	{
		return dataGranularity;
	}
	public void setDataGranularity(String dataGranularity)
	{
		this.dataGranularity = dataGranularity;
	}
	public List<String> getValidRanges()
	{
		return validRanges;
	}
	public void setValidRanges(List<String> validRanges)
	{
		this.validRanges = validRanges;
	}
}
