/*
 *  MIT License
 *
 * Copyright (c) 2018 mnemotron
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package api.finance.yahoo.histquote.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Yahoo Finance
 *
 * JSON: Meta
 * 
 * @author mnemotron
 * @version 1.3.0
 * @since 2018-02-11
 */
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
	
	public Meta()
	{
		this.currency = new String();
		this.symbol = new String();
		this.exchangeName = new String();
		this.instrumentType = new String();
		this.firstTradeDate = new String();
		this.gmtoffset = new String();
		this.timezone = new String();
		this.exchangeTimezoneName = new String();
		this.chartPreviousClose = new String();
		this.currentTradingPeriod = new CurrentTradingPeriod();
		this.dataGranularity = new String();
		this.validRanges = new ArrayList<String>();
	}
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
