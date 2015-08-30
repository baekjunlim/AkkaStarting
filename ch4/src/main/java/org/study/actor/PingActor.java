package org.study.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * "good"이나 "bad" 메시지를 받으면 Ping1Actor라는 자식 액터에게 전달. 
 * "done" 메시지를 받으면 화면에 결과를 출력.
 * @author Baekjun Lim
 */
public class PingActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child;
	
	public PingActor() {
		child = context().actorOf(Props.create(Ping1Actor.class), "ping1Actor");
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
        	String msg = (String)message;
        	if ("good".equals(msg) || "bad".equals(msg)) {
        		child.tell(msg, getSelf());
        	}
        	else if ("done".equals(msg)) {
      		    log.info("all works are successully completed.");
        	}
        }
		else {
    		unhandled(message);
    	}
	}
	
}
