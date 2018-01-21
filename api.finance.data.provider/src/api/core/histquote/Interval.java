package api.core.histquote;

/**
 * ENUM Interval
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public enum Interval
{
	DAY_1("1D"), MONTH_1("1M"), WEEK_1("1W");

	private final String interval;

	Interval(String interval)
	{
		this.interval = interval;
	}

	public String getInterval()
	{
		return this.interval;
	}
}
