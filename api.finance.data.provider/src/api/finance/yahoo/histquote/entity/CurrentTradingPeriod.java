package api.finance.yahoo.histquote.entity;

public class CurrentTradingPeriod
{
	Pre pre;
	Regular regular;
	Post post;
	
	public Pre getPre()
	{
		return pre;
	}
	public void setPre(Pre pre)
	{
		this.pre = pre;
	}
	public Regular getRegular()
	{
		return regular;
	}
	public void setRegular(Regular regular)
	{
		this.regular = regular;
	}
	public Post getPost()
	{
		return post;
	}
	public void setPost(Post post)
	{
		this.post = post;
	}
}
