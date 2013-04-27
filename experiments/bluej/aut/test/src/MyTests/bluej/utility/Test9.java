package MyTests.bluej.utility;

import bluej.utility.GeneralCache;
import junit.framework.TestCase;

/**
 * Testing the bluej.utility.GeneralCache.java that stores
 * the data into the cache and replaces its contents in a 
 * first in first out fashion when the size of the cache is exceeded.
 * 
 * @author Nikhat Farha
 *
 */


public class Test9 extends TestCase{

	protected void setUp() {
		
	}
	protected void tearDown() {
		
	}
	
	public void testGeneralCache() {
		GeneralCache<String,Integer> cache = new GeneralCache<String,Integer>(5);
		cache.put("key1", 1);
		cache.put("key2", 2);
		assertTrue(cache.containsKey("key1"));
		assertTrue(cache.containsKey("key2"));
	}
	
	public void testContainsKey() {
		GeneralCache<String,Integer> cache = new GeneralCache<String,Integer>(2);
		cache.put("key1", 1);
		cache.put("key2", 2);
		//Since the cache size is 2, the key3 replaces key1
		cache.put("key3",3);
	
		assertTrue(cache.containsKey("key2"));
		assertTrue(cache.containsKey("key3"));
		//Does not contain key1!
		assertFalse(cache.containsKey("key1"));
	}
	
	public void testGet() {
		GeneralCache<String,Integer> cache = new GeneralCache<String,Integer>(3);
		cache.put("key1", 1);
		cache.put("key2", 2);
		assertEquals(1,cache.get("key1").intValue());
		assertEquals(2,cache.get("key2").intValue());
	}
	
	public void testClear() {
		GeneralCache<String,Integer> cache = new GeneralCache<String,Integer>(3);
		cache.put("key1", 1);
		cache.put("key2", 2);
		cache.clear();
		assertFalse(cache.containsKey("key1"));
		assertFalse(cache.containsKey("key2"));
	}
}
