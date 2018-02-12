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
package api.core.histquote.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.core.histquote.Attribute;
import api.core.histquote.Interval;

/**
 * BEAN Historical Quotes
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-22
 */
public class HistoricalQuotes
{
	private String tickerID;
	private Interval interval;
	private Calendar from;
	private Calendar to;
	private List<HistoricalQuote> historicalQuoteList;
	private Map<Attribute, String> attributeList;

	public HistoricalQuotes()
	{
		this.historicalQuoteList = new ArrayList<HistoricalQuote>();
		this.attributeList = new HashMap<Attribute, String>();
		this.tickerID = new String();
		this.interval = null;
		this.from = null;
		this.to = null;
	}

	public List<HistoricalQuote> getHistoricalQuoteList()
	{
		return historicalQuoteList;
	}

	public void setHistoricalQuoteList(List<HistoricalQuote> historicalQuoteList)
	{
		this.historicalQuoteList = historicalQuoteList;
	}

	public String getTickerID()
	{
		return tickerID;
	}

	public void setTickerID(String tickerID)
	{
		this.tickerID = tickerID;
	}

	public Interval getInterval()
	{
		return interval;
	}

	public void setInterval(Interval interval)
	{
		this.interval = interval;
	}

	public Calendar getFrom()
	{
		return from;
	}

	public void setFrom(Calendar from)
	{
		this.from = from;
	}

	public Calendar getTo()
	{
		return to;
	}

	public void setTo(Calendar to)
	{
		this.to = to;
	}

	public Map<Attribute, String> getAttributeList()
	{
		return attributeList;
	}

	public void setAttributeList(Map<Attribute, String> attributeList)
	{
		this.attributeList = attributeList;
	}

	public String toString()
	{
		StringBuilder locStringBuilder = new StringBuilder();
		String locLineBreak = System.getProperty("line.separator");

		if (this.tickerID != null)
		{
			locStringBuilder.append("tickerID:" + this.getTickerID() + locLineBreak);
		}
		else
		{
			locStringBuilder.append("tickerID:" + "null" + locLineBreak);
		}

		if (this.interval != null)
		{
			locStringBuilder.append("interval:" + this.getInterval().getInterval() + locLineBreak);
		}
		else
		{
			locStringBuilder.append("interval:" + "null" + locLineBreak);
		}

		if (this.from != null)
		{
			locStringBuilder.append("from:" + this.getFrom().toInstant().toString() + locLineBreak);
		}
		else
		{
			locStringBuilder.append("from:" + "null" + locLineBreak);
		}

		if (this.to != null)
		{
			locStringBuilder.append("to:" + this.getTo().toInstant().toString() + locLineBreak);
		}
		else
		{
			locStringBuilder.append("to:" + "null" + locLineBreak);
		}

		for (Map.Entry<Attribute, String> entry : attributeList.entrySet())
		{
			locStringBuilder.append(entry.getKey() + ":" + entry.getValue() + locLineBreak);
		}

		for (HistoricalQuote historicalQuote : historicalQuoteList)
		{
			locStringBuilder.append(historicalQuote.toString() + locLineBreak);
		}

		return locStringBuilder.toString();
	}
}
