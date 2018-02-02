package api.core.cache;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.impl.cookie.BasicClientCookie;
import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

public class CacheCookieManager
{

	private static final String PATH_CACHE = "./";
	private static final String CACHE_ID_COOKIE = "cacheCookie";
	private static final long CACHE_EXPIRY_DURATION_HOUR = '1';

	private PersistentCacheManager persistentCacheManager;
	private Cache<String, CacheCookieStore> cache;

	public CacheCookieManager()
	{
		this.persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder().with(CacheManagerBuilder.persistence(new File(PATH_CACHE, CACHE_ID_COOKIE))).withCache(CACHE_ID_COOKIE,
				CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, CacheCookieStore.class, ResourcePoolsBuilder.newResourcePoolsBuilder()
						// .heap(10, EntryUnit.ENTRIES)
						// .offheap(1, MemoryUnit.MB)
						.disk(20, MemoryUnit.MB, true))
						.withExpiry(Expirations.timeToLiveExpiration(Duration.of(CACHE_EXPIRY_DURATION_HOUR, TimeUnit.HOURS))))
						.build(true);

		this.loadCache(CACHE_ID_COOKIE);
	}

	public void addToCacheFromCookieStore(String key, CookieStore cookieStore)
	{
		// map to cache cookie store
		CacheCookieStore locCacheCookieStore = new CacheCookieStore();
		ArrayList<CacheCookie> locCookieList = new ArrayList<CacheCookie>();
		Iterator<Cookie> locIterator = cookieStore.getCookies().iterator();

		while (locIterator.hasNext())
		{
			CacheCookie locCacheCookie = new CacheCookie();

			Cookie locCookie = locIterator.next();

			locCacheCookie.setCreationDate(locCookie.getCreationDate());
			locCacheCookie.setDomain(locCookie.getDomain());
			locCacheCookie.setName(locCookie.getName());
			locCacheCookie.setPath(locCookie.getPath());
			locCacheCookie.setValue(locCookie.getValue());
			locCacheCookie.setExpiryDate(locCookie.getExpiryDate());

			locCookieList.add(locCacheCookie);
		}

		locCacheCookieStore.setCookieList(locCookieList);

		// add to chache
		this.cache.put(key, locCacheCookieStore);
	}

	public void addToCache(String key, CacheCookieStore value)
	{
		this.cache.put(key, value);
	}

	public CookieStore getFromCacheCookieStore(String key)
	{
		CookieStore locCookieStore = null;

		CacheCookieStore locCacheCookieStore = this.getFromCache(key);

		if (locCacheCookieStore != null)
		{
			locCookieStore = new BasicCookieStore();

			ArrayList<CacheCookie> locCacheCookieList = locCacheCookieStore.getCookieList();

			for (CacheCookie cacheCookie : locCacheCookieList)
			{
				BasicClientCookie locCookie = new BasicClientCookie(cacheCookie.getName(), cacheCookie.getValue());
				locCookie.setCreationDate(cacheCookie.getCreationDate());
				locCookie.setDomain(cacheCookie.getDomain());
				locCookie.setExpiryDate(cacheCookie.getExpiryDate());
				locCookie.setPath(cacheCookie.getPath());

				locCookieStore.addCookie(locCookie);
			}
		}

		return locCookieStore;
	}

	public CacheCookieStore getFromCache(String key)
	{
		return this.cache.get(key);
	}

	public void loadCache(String cacheID)
	{
		this.cache = this.persistentCacheManager.getCache(cacheID, String.class, CacheCookieStore.class);
	}

	public boolean isCacheValid(String key)
	{
		boolean locResult = true;

		if (this.cache == null)
		{
			locResult = false;
		}
		else if (!this.cache.containsKey(key))
		{
			locResult = false;
		}

		return locResult;
	}

	public void close()
	{
		this.persistentCacheManager.close();
	}
}
