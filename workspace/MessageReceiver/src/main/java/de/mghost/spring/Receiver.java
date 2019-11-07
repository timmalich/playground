package de.mghost.spring;

import org.springframework.stereotype.Component;

@Component
public class Receiver {
	public void receiveTheMessage(String message){
		System.out.println("Received <" + message + ">");
	}
	
}
