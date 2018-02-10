package api.finance.yahoo.histquote.entity;

import java.util.List;

public class Indicators
{
	List<Quote> quote;
	List<Unadjclose> unadjclose;
	List<Adjclose> adjclose;
	
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
