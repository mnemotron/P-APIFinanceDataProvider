package api.core;

/**
 * ENUM API Finance Data Provider
 *  
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public enum API
{
	SIMULATION("SIMULATION"), YAHOO_FINANCE("YAHOO_FINANCE"), GOOGLE_FINANCE("GOOGLE_FINANCE"), STOOQ("STOOQ"), IEXTRADING("IEXTRADING");

	private final String api;

	API(String api)
	{
		this.api = api;
	}

	public String getAPI()
	{
		return this.api;
	}
}
