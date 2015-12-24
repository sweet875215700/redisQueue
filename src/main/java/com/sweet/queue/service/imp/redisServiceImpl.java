package com.sweet.queue.service.imp;

import java.io.Serializable;

import javax.security.auth.callback.Callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPubSub;

import com.sweet.queue.service.redisService;

@Service("redisServiceImpl")
public class redisServiceImpl implements redisService{
	
	 @Autowired
	 private RedisTemplate redisTemplate;

	public boolean insert() {
		boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
			 public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException { 
			 RedisSerializer<String> redisSerializer = redisTemplate .getStringSerializer(); 
			 byte[] key = redisSerializer.serialize("sweet");
			 byte[] value = redisSerializer.serialize("shiwei"); 
			return redisConnection.setNX(key, value); } }); 
		return result;
	}

	public void sentMessage(String channel, Serializable message)throws Exception {
		redisTemplate.convertAndSend(channel, message);
	}
	
	
	

}
