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

import java.util.Calendar;

/**
 * BEAN Historical Quote
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-22
 */
public class HistoricalQuote
{
	private Calendar date;
	private double open;
	private double close;
	private double high;
	private double low;
	private long volume;

	public HistoricalQuote()
	{
		this.date = null;
		this.open = 0;
		this.close = 0;
		this.high = 0;
		this.low = 0;
		this.volume = 0;
	}

	public Calendar getDate()
	{
		return date;
	}

	public void setDate(Calendar date)
	{
		this.date = date;
	}

	public double getOpen()
	{
		return open;
	}

	public void setOpen(double open)
	{
		this.open = open;
	}

	public double getClose()
	{
		return close;
	}

	public void setClose(double close)
	{
		this.close = close;
	}

	public double getHigh()
	{
		return high;
	}

	public void setHigh(double high)
	{
		this.high = high;
	}

	public double getLow()
	{
		return low;
	}

	public void setLow(double low)
	{
		this.low = low;
	}

	public long getVolume()
	{
		return volume;
	}

	public void setVolume(long volume)
	{
		this.volume = volume;
	}
	
	public String toString()
	{
		String locDate = new String();
		
		if (this.date != null)
		{
			locDate = this.date.toInstant().toString();
		}
		else
		{
			locDate = "null";
		}
		
		return new String("{ " + "date:" + locDate + ", " + "close:" + this.close + ", " + "open:" + this.open + ", " + "low:" + this.low + ", " + "high:" + this.high + ", " + "volume:" + this.volume + " }");
	}
}
