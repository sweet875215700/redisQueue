package com.sweet.queue.service.imp;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import sun.util.logging.resources.logging;

import com.sweet.queue.service.RedisLockService;

@Service("redisLockServiceImpl")
public class RedisLockServiceImpl implements RedisLockService {

	Logger log =  Logger.getLogger(RedisLockServiceImpl.class);  
	
	@Autowired
	private RedisTemplate<?, ?> redisTemplate;
	 
	
	public boolean getDistributeLock(final String lockName,Integer trytimes,long sleeptime) throws InterruptedException {
	  
	   if(trytimes<=0){
		   log.debug("重复次数不能小于等于0！");
		   return false;
	   }
	   if(sleeptime<=0){
		   log.debug("等待时间不能小于等于0！");
		   return false;
	   }
	   for(int i=0;i<trytimes;i++){
		   String keyContent = this.getKey(lockName);
		   if(StringUtils.isBlank(keyContent)){
			   boolean setkeyresult = setKey(lockName);
			   if(setkeyresult){
				   return true;
			   }else {
				   Thread.sleep(sleeptime);
				   continue;
			   }
		   }else{
			   Thread.sleep(sleeptime);
			   continue; 
		   }
	   }
	   return false;	 
	}
	
	public boolean releaseLock(String lockName, Integer trytimes, long sleeptime) throws InterruptedException {
		if(trytimes<=0){
			log.debug("重复次数不能小于等于0！");
			return false;
		}
		if(sleeptime<=0){
			   log.debug("等待时间不能小于等于0！");
			   return false;
		}
		for(int i=0;i<trytimes;i++){
			   String keyContent = this.getKey(lockName);
			   if(StringUtils.isBlank(keyContent)){
				   Long setkeyresult = delKey(lockName);
				   if(setkeyresult>0){
					   return true;
				   }else {
					   Thread.sleep(sleeptime);
					   continue;
				   }
			   }else{
				   Thread.sleep(sleeptime);
				   continue; 
			   }
		   }
		return false;
	}
	
	
	private String getKey(final String lockName){
		return redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return new String(connection.get(lockName.getBytes()), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });
	}
	
	private boolean setKey(final String lockName){
		return (Boolean)redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				 RedisSerializer<String> redisSerializer = redisTemplate .getStringSerializer(); 
				 byte[] key = redisSerializer.serialize(lockName);
				 byte[] value = redisSerializer.serialize("locl");
				 return connection.setNX(key, value);
			}
			
		});
	}

   private Long delKey(final String lockName){
	   return (Long)redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				 return connection.del(lockName.getBytes());
			}
			
		});
   }
	
	

}
