package api.yahoo.finance.symbol.entity;

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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExch() {
		return exch;
	}

	public void setExch(String exch) {
		this.exch = exch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExchDisp() {
		return exchDisp;
	}

	public void setExchDisp(String exchDisp) {
		this.exchDisp = exchDisp;
	}

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
