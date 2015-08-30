package org.study.ch8.http;

import akka.actor.ActorRef;
import akka.camel.CamelMessage;
import akka.camel.javaapi.UntypedConsumerActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * 웹서버 역할을 수행하기 위해서 캐멀과 제티를 사용하는 액터 
 * @author Baekjun Lim
 */
public class HttpActor extends UntypedConsumerActor{

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private String uri;
	private ActorRef service;
	private ActorRef sender;
	
	public HttpActor(ActorRef service) {
		this.service = service;
		this.uri = "jetty:http://localhost:8877/akkaStudy";
	}

	public void onReceive(Object message) throws Exception {
		if (message instanceof CamelMessage) {
            this.sender = getSender();
            service.tell("Hello", getSelf());
		} else if (message instanceof String) {
			sender.tell(message, getSelf());
		} else {
			unhandled(message);
		}
	}

	public String getEndpointUri() {
		return uri;
	}

}