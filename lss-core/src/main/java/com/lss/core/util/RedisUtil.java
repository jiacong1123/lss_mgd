package com.lss.core.util;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @ClassName: RedisUtil
 * @Description: redis操作工具类
 * @author xiehz
 * @date 2015年6月4日 下午3:55:43
 *
 */
public class RedisUtil {

	private static Log logger = LogFactory.getLog(RedisUtil.class);

	// Redis服务器IP
	private static String ADDR_ARRAY = PropertyFactory.getPropertyValue("redis", "redis.ip");

	// Redis的端口号
	private static int PORT = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "redis.port"));

	// 访问密码
	private static String AUTH = PropertyFactory.getPropertyValue("redis", "redis.auth");

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "maxActive"));;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "maxIdle"));;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "maxWait"));;

	// 超时时间
	private static int TIMEOUT = Integer.parseInt(PropertyFactory.getPropertyValue("redis", "timeOut"));;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = Boolean.valueOf(PropertyFactory.getPropertyValue("redis", "testOnBorrow"));

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_RETURN = Boolean.valueOf(PropertyFactory.getPropertyValue("redis", "testOnReturn"));

	private static JedisPool jedisPool = null;

	/**
	 * redis过期时间,以秒为单位
	 */
	public final static int EXRP_HOUR = 60 * 60; // 一小时
	public final static int EXRP_DAY = 60 * 60 * 24; // 一天
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 一个月

	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {

			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWait(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			config.setTestOnReturn(TEST_ON_RETURN);
			try{
				jedisPool = new JedisPool(config, ADDR_ARRAY, PORT, TIMEOUT,AUTH);
				Jedis js=jedisPool.getResource();
				jedisPool.returnResource(js);
			}
			catch(Exception ex)
			{
				jedisPool = new JedisPool(config, ADDR_ARRAY, PORT, TIMEOUT, AUTH);
				Jedis js=jedisPool.getResource();
				jedisPool.returnResource(js);
			}
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null) {
			initialPool();
		}
	}

	/**
	 * 同步获取Jedis实例
	 * 
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool == null) {
				poolInit();
			}
			return jedisPool.getResource();
		} catch (Exception e) {
			logger.error("Get jedis error : " + e);
			return null;
		}
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null && jedisPool != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: setString
	 * @Description: 设置string
	 * @param @param key
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public static void setString(String key, String value) {
		value = ObjectUtil.isEmpty(value) ? "" : value;
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
//			logger.error("setString(key,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: setString
	 * @Description: 设置string及过期时间
	 * @param @param key
	 * @param @param seconds
	 * @param @param value
	 * @return void
	 * @throws
	 */
	public static void setString(String key, String value, int seconds) {
		value = ObjectUtil.isEmpty(value) ? "" : value;
		Jedis jedis = getJedis();
		try {
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
//			logger.error("setString(key,seconds,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	/**
	 * 
	 * @Title: getString
	 * @Description: 获取String值
	 * @param @param key
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getString(String key) {
		Jedis jedis = getJedis();
		try {
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
//			logger.error("getString(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setByte(byte[] key, byte[] value) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
//			logger.error("setByte(byte,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setByte(byte[] key, byte[] value, int seconds) {
		Jedis jedis = getJedis();
		try {
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
//			logger.error("setByte(byte,value,seconds) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static byte[] getByte(byte[] key) {
		Jedis jedis = getJedis();
		try {
			byte[] value = jedis.get(key);
			return value;
		} catch (Exception e) {
//			logger.error("getByte(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setObject(String key, Object value) {
		if(value==null)
			return;
		Jedis jedis = getJedis();
		try {
			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
//			logger.error("setObject(byte,value) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setObject(String key, Object value, int seconds) {
		if(value==null)
			return;
		Jedis jedis = getJedis();
		try {
			jedis.setex(key.getBytes(), seconds, SerializeUtil.serialize(value));
		} catch (Exception e) {
//			logger.error("setObject(byte,value,seconds) error : " + e);
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static Object getObject(String key) {
		Jedis jedis = getJedis();
		try {
			byte[] object = jedis.get(key.getBytes());
			return (Object) SerializeUtil.unserialize(object);
		} catch (Exception ex) {
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static Long delete(String key) {
		Jedis jedis = getJedis();
		try {
			return jedis.del(key);
		} catch (Exception e) {
//			logger.error("delString(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static Long delete(byte[] key) {
		Jedis jedis = getJedis();
		try {
			return jedis.del(key);
		} catch (Exception e) {
//			logger.error("delString(key) error : " + e);
			return null;
		} finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setExpire(String key, int secondes) {
		Jedis jedis = getJedis();
		try {
			Object obj = new Object();
			try {
				byte[] object = jedis.get(key.getBytes());
				obj = (Object) SerializeUtil.unserialize(object);
			} catch (Exception e) {
				obj = null;
			}
			if (obj != null)
				jedis.expire(key.getBytes(), secondes);
		} catch (Exception e) {
//			logger.error("setExpire(key) error : " + e);
		}
		// 释放连接
		finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}

	public static void setExpire(byte[] key, int secondes) {
		Jedis jedis = getJedis();
		try {
			if (key != null)
				jedis.expire(key, secondes);
		} catch (Exception e) {
//			logger.error("setExpire(key) error : " + e);
		}
		// 释放连接
		finally {
			if (jedis != null)
				returnResource(jedis);
		}
	}
	
	  public static Map<String, String> hgetAll(String key) {
	        Map<String, String> result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.hgetAll(key);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	  
	 public static int isExistInKeyset(String key, String hName) {
			
			int result = 0;
			Jedis jedis = getJedis();
			try{
				if(jedis.hexists(hName, key))
					result = 1;
			}catch(Exception e){
				//throw e;
			}finally {
	        	returnResource(jedis);
	        }
			return result;
	}
	
	 public static int keysetDel(String key, String hName) {
			
			int result = 0;
			Jedis jedis = getJedis();
			try{
				jedis.hdel(hName, key);
				result = 1;
				
			}catch(Exception e){
			}finally {
	        	returnResource(jedis);
	        }
			return result;
	}
	 
	 public static int addKeyToKeyset(String key, String value, String hName) {
			
			int result = 0;
			Jedis jedis = getJedis();
			try{
				jedis.hset(hName, key, value);
				result = 1;
			}catch(Exception e){
			}finally {
	        	returnResource(jedis);
	        }
			return result;
	 }
	 
	 public static Boolean exists(String key) {
	        Boolean result = false;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.exists(key);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	  
	 public static Long hdel(String key, String field) {
	        Long result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.hdel(key, field);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	  
	 public static Long hset(String key, String field, String value) {
	        Long result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.hset(key, field, value);

	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	  
	 public static Long incr(String key) {
	        Long result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.incr(key);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	  }
	 
	 public static String hget(String key, String field) {
	        String result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.hget(key, field);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	 
	 public static Long sadd(String key, String member) {
	        Long result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.sadd(key, member);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	        
	 public static Set<String> smembers(String key) {
	        Set<String> result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.smembers(key);

	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	  
	 public static Long srem(String key, String member) {
		 	Jedis jedis = getJedis();
	        Long result = null;
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.srem(key, member);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }

	 public static String spop(String key) {
		 	Jedis jedis = getJedis();
	        String result = null;
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.spop(key);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	  }
	   
	 public static Long scard(String key) {
		 	Jedis jedis = getJedis();
	        Long result = null;
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.scard(key);

	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	   
	 public static Boolean sismember(String key, String member) {
		 	Jedis jedis = getJedis();
	        Boolean result = null;
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.sismember(key, member);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }

	 public static String srandmember(String key) {
	    	Jedis jedis = getJedis();
	        String result = null;
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.srandmember(key);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	 
	 public static Long hincrBy(final String key, final String field, final long value){
		 
		 Jedis jedis = getJedis();
		 	Long result = null;
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.hincrBy(key, field, value);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	 
	 public static Long lpush(String key, String ...string) {
	        Long result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.lpush(key, string);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	 
	 public static Long lpush(final byte[] key, final byte[]... strings) {
		 
		 Long result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.lpush(key, strings);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	 
	 public static Long rpush(final byte[] key, final byte[]... strings) {
		 
		 Long result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.rpush(key, strings);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
	 
	 public static List<byte[]> lrange(final byte[] key, final int start, final int end) {
		 
		 List<byte[]> result = null;
	        Jedis jedis = getJedis();
	        if (jedis == null) {
	            return result;
	        }
	        try {
	            result = jedis.lrange(key, start, end);
	        } catch (Exception e) {
	        } finally {
	        	returnResource(jedis);
	        }
	        return result;
	 }
}
