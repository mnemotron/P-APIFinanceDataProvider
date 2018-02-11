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
 * JSON: Indicators
 * 
 * @author mnemotron
 * @version 1.3.0
 * @since 2018-02-11
 */
public class Indicators
{
	List<Quote> quote;
	List<Unadjclose> unadjclose;
	List<Adjclose> adjclose;
	
	public Indicators()
	{
		this.quote = new ArrayList<Quote>();
		this.unadjclose = new ArrayList<Unadjclose>();
		this.adjclose = new ArrayList<Adjclose>();
	}
	
	public List<Quote> getQuote()
	{
		return quote;
	}
	public void setQuote(List<Quote> quote)
	{
		this.quote = quote;
	}
	public List<Unadjclose> getUnadjclose()
	{
		return unadjclose;
	}
	public void setUnadjclose(List<Unadjclose> unadjclose)
	{
		this.unadjclose = unadjclose;
	}
	public List<Adjclose> getAdjclose()
	{
		return adjclose;
	}
	public void setAdjclose(List<Adjclose> adjclose)
	{
		this.adjclose = adjclose;
	}
}
