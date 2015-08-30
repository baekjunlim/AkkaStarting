package org.study.ch8.service;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * 백엔드 서비스의 역할을 흉내내는 액터. 
 * 메시지가 전달되면 단순히 "PING N" 메시지를 리턴한다.
 * N은 PING이 리턴될 때마다 1씩 증가하는 정수. 
 * @author Baekjun Lim
 */
public class PingService extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private int count = 0;
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
        	String msg = (String)message;
        	getSender().tell("PING: " + count++, getSelf());
        } else {
        	unhandled(message);
        }
	}
	
}
