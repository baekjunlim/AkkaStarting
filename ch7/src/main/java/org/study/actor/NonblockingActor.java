package org.study.actor;

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
 * 아카의 퓨처를 이용해서 non-blocking 동작을 보여주는 액터
 * @author Baekjun Lim
 */
public class NonblockingActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child;
	private Timeout timeout = new Timeout(Duration.create(5, "seconds"));
	private final ExecutionContext ec;
	
	public NonblockingActor() {
		child = context().actorOf(Props.create(CalculationActor.class), "calculationActor");
		ec = context().system().dispatcher();
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Integer) {
            Future<Object> future = Patterns.ask(child, message, timeout);
            
            // onSuccess, onComplete, onFailure 등은 blocking 동작이 아니다.
            future.onSuccess(new SaySuccess<Object>(), ec);
            future.onComplete(new SayComplete<Object>(), ec);
            future.onFailure(new SayFailure<Object>(), ec);

		} else if (message instanceof String) {
			log.info("NonblockingActor received a messasge: " + message);
		}
	}
	
	public final class SaySuccess<T> extends OnSuccess<T> {
	    @Override public final void onSuccess(T result) {
	    	log.info("Succeeded with " + result);
		}
	}
	
	public final class SayFailure<T> extends OnFailure {
	    @Override public final void onFailure(Throwable t) {
	    	log.info("Failed with " + t);
		}
	}
	
	public final class SayComplete<T> extends OnComplete<T> {
	    @Override public final void onComplete(Throwable t, T result) {
	    	log.info("Completed.");
		}
	}
}
