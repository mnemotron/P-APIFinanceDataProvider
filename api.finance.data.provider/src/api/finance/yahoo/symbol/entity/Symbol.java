package api.finance.yahoo.symbol.entity;

/**
 * Symbol Result
 * @author  mnemotron
 * @version 1.0.0
 * @since 2018-01-01
 */
public class Symbol {
	
	private String symbol;
	private String name;
	private String exch;
	private String type;
	private String exchDisp;
	private String typeDisp;	

	public Symbol()
	{
		
	}

	/**
	 * Yahoo Finance ticker ID
	 * @return Ticker ID
	 */
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * Name of the asset
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Exchange
	 * @return Exchange
	 */
	public String getExch() {
		return exch;
	}

	public void setExch(String exch) {
		this.exch = exch;
	}

	/**
	 * Asset type i.e. "S"hare
	 * @return Asset Type
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Exchange display string
	 * @return Exchange display string
	 */
	public String getExchDisp() {
		return exchDisp;
	}

	public void setExchDisp(String exchDisp) {
		this.exchDisp = exchDisp;
	}

	/**
	 * Asset type display string
	 * @return Asset type display string
	 */
	public String getTypeDisp() {
		return typeDisp;
	}

	public void setTypeDisp(String typeDisp) {
		this.typeDisp = typeDisp;
	}
	
	public String toString()
	{
		return new String("{ " + "symbol:" + this.symbol + "; " + "name:" + this.name + "; " + "exch:" + this.exch + "; " + "type:" + this.type + "; " + "exchDisp:" + this.exchDisp + "; " + "typeDisp:" + this.typeDisp + " }");
	}
}
