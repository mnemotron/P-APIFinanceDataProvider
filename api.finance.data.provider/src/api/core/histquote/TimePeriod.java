package api.core.histquote;

/**
 * ENUM Time Period
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public enum TimePeriod
{
	DAY_1("1D"), WEEK_1("1W"), MONTH_1("1M"), MONTH_6("6M"), YEAR_1("1Y"), YEAR_3("3Y"), YEAR_5("5Y");

	private final String timePeriod;

	TimePeriod(String timePeriod)
	{
		this.timePeriod = timePeriod;
	}

	public String getTimePeriod()
	{
		return this.timePeriod;
	}
}
