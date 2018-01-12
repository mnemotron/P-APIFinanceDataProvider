package api.yahoo.finance;

import java.net.URLEncoder;

public class QueryString {

	private static final String ENCODING_UTF8 = "UTF-8";

	private String query;

	public QueryString() {
		this.query = new String();
	}

	public void add(String name, String value) throws Exception {
		if (this.query.isEmpty()) {
			this.query += name + "=" + value;
		} else {
			this.query += "&" + name + "=" + value;
		}
	}

	public String getQueryEncode() throws Exception {
		return URLEncoder.encode(this.query, QueryString.ENCODING_UTF8);
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
