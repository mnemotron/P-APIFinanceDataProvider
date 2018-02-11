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
 * JSON: Quote
 * 
 * @author mnemotron
 * @version 1.3.0
 * @since 2018-02-11
 */
public class Quote
{
	List<String> volume;
	List<String> low;
	List<String> close;
	List<String> high;
	List<String> open;
	
	public Quote()
	{
		this.volume = new ArrayList<String>();
		this.low = new ArrayList<String>();
		this.close = new ArrayList<String>();
		this.high = new ArrayList<String>();
		this.open = new ArrayList<String>();
	}
	
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
