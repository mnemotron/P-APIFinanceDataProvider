package api.core.cache;

import java.io.File;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

public class CacheManager {
	
	private static final String PATH_CACHE = "./";
	
	private PersistentCacheManager persistentCacheManager;
	private Cache<String, CacheCookieStore> cache;
	
	public CacheManager(String cacheID)
	{
		this.persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.with(CacheManagerBuilder.persistence(new File(PATH_CACHE, cacheID))) 
				.withCache(cacheID, CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, CacheCookieStore.class, ResourcePoolsBuilder.newResourcePoolsBuilder()
						.heap(10, EntryUnit.ENTRIES)
						.offheap(1, MemoryUnit.MB) 
						.disk(20, MemoryUnit.MB, true) 
				) 
					).build(true);
		
		this.loadCache(cacheID);
		
		//CacheConfiguration<Long, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//      ResourcePoolsBuilder.heap(100)) 
//  .withExpiry(Expirations.timeToLiveExpiration(Duration.of(20, TimeUnit.SECONDS))) 
//  .build();
	}
	
	public void addToCache(String key, CacheCookieStore value)
	{
		this.cache.put(key, value);
	}
	
	public Object getFromCache(String key)
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
		else if(!this.cache.containsKey(key))
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
