package org.study.ch8;


import org.study.ch8.service.PingService;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * 아카의 클러스터링을 보여주기 위한 메인 클래스
 * @author Baekjun Lim
 */
public class Main {

	/** 백엔드 서비스 역할을 수행하는 PingService 액터를 생성한다. */
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("ClusterSystem");
        ActorRef pingService = actorSystem.actorOf(Props.create(PingService.class), "pingService");
    }
}
