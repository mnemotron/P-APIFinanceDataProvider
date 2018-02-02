/*
 * MIT License
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
package api.core.ticker.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Tickers
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public class Tickers
{
	private String query;
	private List<Ticker> tickerList;

	public Tickers()
	{
		this.query = new String();
		this.tickerList = new ArrayList<Ticker>();
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public List<Ticker> getTickerList()
	{
		return tickerList;
	}

	public void setTickerList(List<Ticker> tickerList)
	{
		this.tickerList = tickerList;
	}

	public String toString()
	{
		StringBuilder locStringBuilder = new StringBuilder();

		locStringBuilder.append("query:" + this.getQuery() + "\n");

		for (Ticker ticker : tickerList)
		{
			locStringBuilder.append(ticker.toString() + "\n");
		}

		return locStringBuilder.toString();
	}
}
