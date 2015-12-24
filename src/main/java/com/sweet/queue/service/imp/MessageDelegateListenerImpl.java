package com.sweet.queue.service.imp;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.sweet.queue.service.MessageDelegateListener;

public class MessageDelegateListenerImpl implements MessageDelegateListener {

	public void handleMessage(Serializable message) throws Exception {
		  if(message == null){
	            System.out.println("null");
	        } else if(message.getClass().isArray()){
	            System.out.println(Arrays.toString((Object[])message));
	        } else if(message instanceof List<?>) {
	            System.out.println(message);
	        } else if(message instanceof Map<? , ?>) {
	            System.out.println(message);
	        } else {
	            System.out.println(ToStringBuilder.reflectionToString(message));
	        }

	}

}
