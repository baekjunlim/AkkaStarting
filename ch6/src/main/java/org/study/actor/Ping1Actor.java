package org.study.actor;


import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * 정수를 받으면 자신의 해시코드 값과 함께 화면에 출력하는 간단한 액터
 * @author Baekjun Lim
 */
public class Ping1Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Integer) {
			Integer msg = (Integer)message;
		    log.info("Ping1Actor({}) received {}", hashCode(), msg);
		    work(msg);
		}
	}
	
	private void work(Integer n) throws Exception {
		log.info("Ping1Actor({}) working on {}", hashCode(), n);
		Thread.sleep(1000); // 실전에서는 절대 금물!!!
		log.info("Ping1Actor({}) completed ", hashCode());
	}
}
