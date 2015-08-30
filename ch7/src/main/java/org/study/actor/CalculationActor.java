package org.study.actor;


import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CalculationActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Integer) {
			Integer msg = (Integer)message;
		    log.info("CalculationActor received {}", msg);
		    work(msg);
		    getSender().tell(msg * 2, getSelf());
		}
		else {
			unhandled(message);
		}
	}
	
	private void work(Integer n) throws Exception {
		log.info("CalculationActor working on " + n);
		Thread.sleep(1000); // 실전에서는 절대 금물!!!!
		log.info("CalculationActor completed " + n);
	}
}
