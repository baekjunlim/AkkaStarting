package org.study.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Ping3Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	public Ping3Actor() {
		log.info("Ping3Actor constructgor..");
	}
	
	@Override
	public void preRestart(Throwable reason, scala.Option<Object> message) {
		log.info("Ping3Actor preRestart..");
	}

	@Override
	public void postRestart(Throwable reason) {
		log.info("Ping3Actor postRestart..");
	}

	@Override
	public void postStop() {
		log.info("Ping3Actor postStop..");
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			String msg = (String)message;
			if ("good".equals(msg)) {
			    goodWork();
			    getSender().tell("done", getSelf());
			}
			else if ("bad".equals(msg)) {
				badWork();
			}
		}
		else {
			unhandled(message);
		}
	}

	private void goodWork() throws Exception {
		log.info("Ping3Actor is good.");
	}
	
	/** 일부러 NullPointerException을 발생시킨다 */
	private void badWork() throws Exception {
		throw new NullPointerException();
	}
}
