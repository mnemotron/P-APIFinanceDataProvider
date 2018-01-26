package api.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class ConvertValueToDate<T> extends AbstractBeanField<T>
{
	public ConvertValueToDate()
	{

	}

	@Override
	protected Object convert(String date) throws CsvDataTypeMismatchException, CsvConstraintViolationException
	{
		SimpleDateFormat locDateFormatter = new SimpleDateFormat("dd-MMM-yy");
		Date locDate = null;
		
		try
		{
			locDate = locDateFormatter.parse(date);
		}
		catch (ParseException e)
		{
			CsvDataTypeMismatchException csve = new CsvDataTypeMismatchException(e.getMessage());
			csve.initCause(e);
			throw csve;

		}
		
		return locDate;
	}

}
