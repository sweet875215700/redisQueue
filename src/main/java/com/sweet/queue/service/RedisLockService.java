package com.sweet.queue.service;

public interface RedisLockService {
	
	/**
	 * 	
	 * @param lockName ������
	 * @param trytimes �ظ�����
	 * @param sleeptime ����ʱ��
	 * @return
	 */
	public boolean getDistributeLock(String lockName,Integer trytimes,long sleeptime) throws InterruptedException;
	
	
	/***
	 * 
	 * @param lockName ������
	 * @param trytimes �ظ�����
	 * @param sleeptime ����ʱ��
	 * @return
	 * @throws InterruptedException
	 */
	public boolean releaseLock(String lockName,Integer trytimes,long sleeptime) throws InterruptedException;
	
}
