package org.study.actor;

import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.OnComplete;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;

/**
 * 아카의 퓨처를 이용해서 블로킹 동작을 보여주는 액터 
 * @author Baekjun Lim
 */
public class BlockingActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child;
	private Timeout timeout = new Timeout(Duration.create(5, "seconds"));
	private final ExecutionContext ec;
	
	public BlockingActor() {
		child = context().actorOf(Props.create(CalculationActor.class), "calculationActor");
		ec = context().system().dispatcher();
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Integer) {
            Future<Object> future = Patterns.ask(child, message, timeout);
            Integer result = (Integer) Await.result(future, timeout.duration()); // 블로킹!!!! 
            log.info("Final result is " + (result + 1));
           
		} else if (message instanceof String) {
			log.info("BlockingActor received a messasge: " + message);
		}
	}
}
