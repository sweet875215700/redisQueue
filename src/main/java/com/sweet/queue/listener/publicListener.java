package com.sweet.queue.listener;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import com.sweet.queue.bean.User;

@Service(value = "listener")
public class publicListener implements MessageListener {

	
	@Resource(name = "serialization")
	private JdkSerializationRedisSerializer serialization = null;
	
	public void onMessage(Message message, byte[] arg1) {
		User user = (User) serialization.deserialize(message.getBody());
		System.out.println(user.getUserId());
		System.out.println(user.getUserName());
		System.out.println(user.toString());
	}

}
