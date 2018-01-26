package api.finance.google.symbol.entity;

/**
 * Symbol Result
 * @author  mnemotron
 * @version 1.1.0
 * @since 2018-01-26
 */
public class FGSymbol
{
	private String t;
	private String n;
	private String e;
	private String id;

	public FGSymbol()
	{

	}

	public String getT()
	{
		return t;
	}

	public void setT(String t)
	{
		this.t = t;
	}

	public String getN()
	{
		return n;
	}

	public void setN(String n)
	{
		this.n = n;
	}

	public String getE()
	{
		return e;
	}

	public void setE(String e)
	{
		this.e = e;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
