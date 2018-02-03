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
package api.finance.stooq.histquote.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.util.Date;

/**
 * BEAN Stooq Historical Quote
 * 
 * @author mnemotron
 * @version 1.2.0
 * @since 2018-02-03
 */
public class FSBeanHistoricalQuote
{

	@CsvBindByName
	@CsvDate("yyyy-MM-dd")
	private Date date;

	@CsvBindByName
	private String open;

	@CsvBindByName
	private String high;

	@CsvBindByName
	private String low;

	@CsvBindByName
	private String close;

	@CsvBindByName
	private String volume;

	public FSBeanHistoricalQuote()
	{
		this.date = null;
		this.open = new String();
		this.high = new String();
		this.low = new String();
		this.close = new String();
		this.volume = new String();
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public String getOpen()
	{
		return open;
	}

	public void setOpen(String open)
	{
		this.open = open;
	}

	public String getHigh()
	{
		return high;
	}

	public void setHigh(String high)
	{
		this.high = high;
	}

	public String getLow()
	{
		return low;
	}

	public void setLow(String low)
	{
		this.low = low;
	}

	public String getClose()
	{
		return close;
	}

	public void setClose(String close)
	{
		this.close = close;
	}

	public String getVolume()
	{
		return volume;
	}

	public void setVolume(String volume)
	{
		this.volume = volume;
	}
}