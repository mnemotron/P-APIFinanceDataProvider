package api.finance.yahoo.histquote.entity;

import java.util.List;

public class Chart
{
	List<Result> result;
	Error error;
	public List<Result> getResult()
	{
		return result;
	}
	public void setResult(List<Result> result)
	{
		this.result = result;
	}
	public Error getError()
	{
		return error;
	}
	public void setError(Error error)
	{
		this.error = error;
	}
}
