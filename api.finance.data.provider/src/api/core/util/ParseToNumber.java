package api.core.util;

public class ParseToNumber
{
	public static double parseStringToDouble(String string)
	{
		double locDouble = 0;

		try
		{
			locDouble = Double.parseDouble(string);
		}
		catch (Exception e)
		{
			// return initial value
		}

		return locDouble;
	}

	public static long parseStringToLong(String string)
	{
		long locLong = 0;

		try
		{
			locLong = Long.parseLong(string);
		}
		catch (Exception e)
		{
			// return initial value
		}

		return locLong;
	}
}
