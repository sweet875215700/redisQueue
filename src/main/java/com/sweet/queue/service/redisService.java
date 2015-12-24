package com.sweet.queue.service;

import java.io.Serializable;

public interface redisService {
	
	/**
	 * 
	 * @return
	 */
	public boolean insert() throws Exception;
	
	
	/**
	 * 
	 * @param channel
	 * @param message
	 * @throws Exception
	 */
	public void sentMessage(String channel, Serializable message) throws Exception;
	

}
