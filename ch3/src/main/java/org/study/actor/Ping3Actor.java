package org.study.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * 계층구조 트리의 최하위 leaf에 해당하는 액터
 * @author Baekjun Lim
 */
public class Ping3Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			String msg = (String)message;
			if ("work".equals(msg)) {
			    log.info("Ping3 received {}", msg);
			    work();
			    getSender().tell("done", getSelf());
			}
		}
	}

	private void work() throws Exception {
		Thread.sleep(1000); // 실전에서는 절대 금물!!!
		log.info("Ping3 working...");
	}
}
