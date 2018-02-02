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
package api.finance.google.histquote.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * BEAN Finance Google Historical Quotes
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-26
 */
public class FGBeanHistoricalQuotes
{

	private String tickerID;
	private Calendar from;
	private Calendar to;
	private List<FGBeanHistoricalQuote> histQuoteList;

	public FGBeanHistoricalQuotes()
	{
		this.tickerID = new String();
		this.from = null;
		this.to = null;
		this.histQuoteList = new ArrayList<FGBeanHistoricalQuote>();
	}

	public String getTickerID()
	{
		return tickerID;
	}

	public void setTickerID(String tickerID)
	{
		this.tickerID = tickerID;
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

	public List<FGBeanHistoricalQuote> getHistQuoteList()
	{
		return histQuoteList;
	}

	public void setHistQuoteList(List<FGBeanHistoricalQuote> histQuoteList)
	{
		this.histQuoteList = histQuoteList;
	}
}
