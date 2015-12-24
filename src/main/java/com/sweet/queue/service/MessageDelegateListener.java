package com.sweet.queue.service;

import java.io.Serializable;

public interface MessageDelegateListener {

	public void handleMessage(Serializable message) throws Exception;
}
