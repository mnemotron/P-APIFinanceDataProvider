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
package api.finance.yahoo.symbol.entity;

/**
 * Symbol Result
 * 
 * @author mnemotron
 * @version 1.0.0
 * @since 2018-01-01
 */
public class Symbol
{

	private String symbol;
	private String name;
	private String exch;
	private String type;
	private String exchDisp;
	private String typeDisp;

	public Symbol()
	{
		this.symbol = new String();
		this.name = new String();
		this.exch = new String();
		this.type = new String();
		this.exchDisp = new String();
		this.typeDisp = new String();
	}

	/**
	 * Yahoo Finance ticker ID
	 * 
	 * @return Ticker ID
	 */
	public String getSymbol()
	{
		return symbol;
	}

	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}

	/**
	 * Name of the asset
	 * 
	 * @return Name
	 */
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Exchange
	 * 
	 * @return Exchange
	 */
	public String getExch()
	{
		return exch;
	}

	public void setExch(String exch)
	{
		this.exch = exch;
	}

	/**
	 * Asset type i.e. "S"hare
	 * 
	 * @return Asset Type
	 */
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Exchange display string
	 * 
	 * @return Exchange display string
	 */
	public String getExchDisp()
	{
		return exchDisp;
	}

	public void setExchDisp(String exchDisp)
	{
		this.exchDisp = exchDisp;
	}

	/**
	 * Asset type display string
	 * 
	 * @return Asset type display string
	 */
	public String getTypeDisp()
	{
		return typeDisp;
	}

	public void setTypeDisp(String typeDisp)
	{
		this.typeDisp = typeDisp;
	}

	public String toString()
	{
		return new String("{ " + "symbol:" + this.symbol + ", " + "name:" + this.name + ", " + "exch:" + this.exch + ", " + "type:" + this.type + ", " + "exchDisp:" + this.exchDisp + ", "
				+ "typeDisp:" + this.typeDisp + " }");
	}
}
