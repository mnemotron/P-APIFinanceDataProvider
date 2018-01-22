package api.core.http;

/**
 * ENUM Scheme
 * 
 * @author mnemotron
 * @version 1.1.0
 * @since 2018-01-20
 */
public enum Scheme {

	HTTP("http"), HTTPS("https");

	private final String scheme;

	Scheme(String scheme) {
		this.scheme = scheme;
	}

	public String getScheme() {
		return this.scheme;
	}
}
