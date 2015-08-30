package org.study;

import org.study.actor.PingActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * 아카의 Let It Crash 철학을 보여주기 위한 메인 클래스
 * @author Baekjun Lim
 */
public class GoodMain {

	public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");
        ping.tell("good", ActorRef.noSender());
	}

}
