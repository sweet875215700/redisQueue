package com.sweet.queue.service;

public interface RedisLockService {
	
	/**
	 * 	
	 * @param lockName 锁名称
	 * @param trytimes 重复次数
	 * @param sleeptime 休眠时间
	 * @return
	 */
	public boolean getDistributeLock(String lockName,Integer trytimes,long sleeptime) throws InterruptedException;
	
	
	/***
	 * 
	 * @param lockName 锁名称
	 * @param trytimes 重复次数
	 * @param sleeptime 休眠时间
	 * @return
	 * @throws InterruptedException
	 */
	public boolean releaseLock(String lockName,Integer trytimes,long sleeptime) throws InterruptedException;
	
}
