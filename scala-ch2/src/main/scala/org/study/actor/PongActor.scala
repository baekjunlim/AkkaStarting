package org.study.actor

import akka.actor.Actor
import akka.actor.actorRef2Scala
import akka.event.Logging

class PongActor extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case msg @ "Ping" => { 
      log.info("Pong received {}", msg); 
      sender ! "Pong" 
    }
    case x @ _ => log.info(">>> received {}", x)
  }
}