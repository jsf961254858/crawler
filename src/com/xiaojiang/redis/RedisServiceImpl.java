package com.xiaojiang.redis;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.xiaojiang.exception.RedisException;
import com.xiaojiang.exception.ServerException;
import com.xiaojiang.exception.ZYBException;
import com.xiaojiang.util.StringUtil;

@Service(value="redisService")
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisClient redisClient;
	
	/*
	public RedisServiceImpl(RedisClient redisClient) {
		this.redisClient = redisClient;
	}*/
	
	@Override
	public long nextId() throws ZYBException {
		return incrementId();
	}

	private long incrementId() throws ZYBException{
		Jedis jedis = null;
		
		try {
			jedis = redisClient.getClient();
			if(jedis == null)
				throw new ServerException("没有取到redis连接, incrementId失败", null);
			
			String redisId = jedis.get("redisId");
			if(redisId == null || "".equals(redisId))
				redisId = "1000";
			
			long id = Long.valueOf(redisId);
			id += 1;
			jedis.set("redisId", id+"");
			
			return id;
			
		} catch (Exception e) {
			throw new ServerException(e.getMessage(), null);
		} finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public boolean isContainsKey(String key) throws ZYBException {
		
		Jedis jedis = null;
		try {
			jedis = redisClient.getClient();
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			return jedis.exists(key);
		} catch (Exception e) {
			throw new ServerException(e.getMessage(), null);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
		
	}

	@Override
	public void putStringReplace(String key, String value, Integer expiredDate)
			throws ZYBException {
		if(StringUtil.isEmpty(key))
			throw new ServerException("推送通知时，未知key值", null);
		
		if(StringUtil.isEmpty(value))
			throw new ServerException("推送通知时，未知通知内容", null);
		
		Jedis jedis = null;
		try {
		
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			
			jedis.set(key, value);
			
			//设置key的生存时间
			if(expiredDate != null)
				jedis.expire(key, expiredDate);
			
		} catch (Exception e) {
			throw new ServerException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
		
	}

	@Override
	public void putStringList(String key, String value, Integer expiredDate)
			throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			jedis.lpush(key, value);
			
			if(expiredDate != null)
				jedis.expireAt(key, expiredDate);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
		
	}

	@Override
	public void putStringAppend(String key, String value, Integer expiredDate)
			throws ZYBException {
		if(StringUtil.isEmpty(key))
			throw new ServerException("推送通知时，未知key值", null);
		
		if(StringUtil.isEmpty(value))
			throw new ServerException("推送通知时，未知通知内容", null);
		
		Jedis jedis = null;
		try {
		
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			
			jedis.append(key, value);
			
			if(expiredDate != null)
				jedis.expire(key, expiredDate);
			
			
		} catch (Exception e) {
			throw new ServerException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
		
	}

	@Override
	public void putMap(String key, Map<String, String> map, Integer expiredDate)
			throws ZYBException {
		Jedis jedis = null;
		try {
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			
			jedis.hmset(key, map);
			
			if(expiredDate != null)
				jedis.expireAt(key, expiredDate);
			
		} catch (Exception e) {
			throw new ServerException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
		
	}

	@Override
	public void putSet(String key, String value, Integer expiredDate)
			throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			jedis.sadd(key, value);
		
			if(expiredDate != null)
				jedis.expireAt(key, expiredDate);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
		
	}

	@Override
	public String popSet(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			return jedis.spop(key);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public Set<String> getKeys(String notifyType) throws ZYBException {
		Jedis jedis = null;
		try {
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			
			Set<String> set = jedis.keys(notifyType+"*");
		
			return set;
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public String getStringAndDelete(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			String value = getString(key);
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			
			jedis.del(key);
			
			return value;
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public String getString(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			return jedis.get(key);
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public Map<String, String> getMapAndDelete(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			Map<String, String> map = getMap(key);
			
			jedis = redisClient.getClient();
		
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			
			jedis.del(key);
			
			return map;
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public Map<String, String> getMap(String key) throws ZYBException {
		Jedis jedis = null;
		try {
		
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			
			Map<String, String> map = jedis.hgetAll(key);
		
			return map;
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public List<String> getList(String key, Long start, Long limit)
			throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			
			long count = jedis.llen(key);
			
			start = start == null?0:start;
			start = start <0 ?0:start;
			
			if(limit == null || limit <= 0){
				limit = count;
			}else{
				if(limit > count){
					limit = count;
				}
			}
			
			List<String> values = redisClient.getClient().lrange(key, start, limit);
			
			if(values == null)
				return Collections.emptyList();
		
			return values;
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public List<String> getAllListAndDelete(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
			
			List<String> values = jedis.lrange(key, 0, -1);
			
			jedis.del(key);
		
			return values;
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public Set<String> getSet(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			return jedis.smembers(key);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public String getPopList(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			return jedis.lpop(key);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public String getIndexList(String key, int index) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			return jedis.lindex(key, index);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public Long getListSize(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			return jedis.llen(key);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public Long getMapSize(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			return jedis.hlen(key);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public Long getSetSize(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}
		
			return jedis.scard(key);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}

	@Override
	public void delete(String key) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}

			jedis.del(key);
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
		
	}

	@Override
	public void setMapFieldValue(String key, String field, String value,
			Integer expiredDate) throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}

			jedis.hset(key, field, value);
			
			if(expiredDate != null)
				jedis.expire(key, expiredDate);
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
		
	}

	@Override
	public String getMapFieldValue(String key, String field)
			throws ZYBException {
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				throw new ServerException("没有取到redis连接", null);
			}

			return jedis.hget(key, field);
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}
	}
	
	
	@Override
	public void doTest(String key, String field) throws ZYBException{
		long d1 = System.currentTimeMillis();
		
		Jedis jedis = null;
		try {
			
			jedis = redisClient.getClient();
			
			if(jedis == null){
				System.out.println("没有取到redis连接");
			}
		
			String value = jedis.hget(new Random().nextInt(1000000)+"", field);
			
			long d2 = System.currentTimeMillis();
			
			System.out.println(value+"    "+(d2-d1));
			
		} catch (Exception e) {
			throw new RedisException(e.getMessage(), e);
		}finally{
			if(jedis != null){
				redisClient.returnJedisPool(jedis);
			}
		}

	}

	public RedisClient getRedisClient() {
		return redisClient;
	}

	public void setRedisClient(RedisClient redisClient) {
		this.redisClient = redisClient;
	}

}
